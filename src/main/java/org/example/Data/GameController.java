package org.example.Data;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class GameController {
    private final Connection connection;
    Logger logger = Logger.getLogger(GameController.class.getName());

    public GameController(Connection connection, String path, boolean choose) {
        this.connection = connection;
        logger.info("Соединение открыто!");
        if (!choose) {
            createTable();
            readFile(path);
        }
    }

    private void createTable() {
        String firstQuery = "DROP TABLE IF EXISTS games";
        String secondQuery =
                "CREATE TABLE IF NOT EXISTS games(\n" +
                        "id INT PRIMARY KEY,\n" +
                        "name_game VARCHAR(250),\n" +
                        "platform VARCHAR(250),\n" +
                        "year_game VARCHAR(250),\n" +
                        "genre VARCHAR(250),\n" +
                        "publisher VARCHAR(250),\n" +
                        "na_sales DOUBLE,\n" +
                        "eu_sales DOUBLE,\n" +
                        "jp_sales DOUBLE,\n" +
                        "other_sales DOUBLE,\n" +
                        "global_sales DOUBLE\n" +
                        ");";
        try {
            Statement statement = connection.createStatement();
            statement.execute(firstQuery);
            statement.execute(secondQuery);
            statement.close();
            logger.info("Таблица создана.");
        } catch (SQLException e) {
            logger.info("Ошибка в создании таблицы");
            System.exit(0);
        }
    }

    private void readFile(String path) {
        try {

            FileReader filereader = new FileReader(path);

            CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                for (int i = 0; i < nextRecord.length; i++) {
                    nextRecord[i] = nextRecord[i].replaceAll("'", "");
                }
                Game game = getGame(nextRecord);
                if (game != null) {
                    insertValues(game);
                }
            }
            logger.info("Данные добавлены.");
        } catch (Exception e) {
           logger.info("Ошибка в добавлении данных.");
        }
    }

    private void insertValues(Game game) {
        String query = "INSERT INTO games " +
                "(id, name_game, platform, year_game, genre, publisher, na_sales, eu_sales, " +
                "jp_sales, other_sales, global_sales) " +
                "VALUES (" + game.getId() + ", '" + game.getName() + "', " +
                "'" + game.getPlatform() + "', '" + game.getYear() + "', " +
                "'" + game.getGenre() + "', '" + game.getPublisher() + "', " + game.getNaSales() + ", " +
                "" + game.getEuSales() + ", " + game.getJpSales() + ", " +
                "" + game.getOtherSales() + ", " + game.getGlobalSales() + ")";
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            logger.info("insertValues Error: " + game.toString());
        }
    }

    private Game getGame(String[] data) {
        Game game = new Game();
        try {
            game.setId(Integer.valueOf(data[0]));
            game.setName((data[1]));
            game.setPlatform((data[2]));
            game.setYear(data[3]);
            game.setGenre((data[4]));
            game.setPublisher((data[5]));
            game.setNaSales(Double.valueOf(data[6]));
            game.setEuSales(Double.valueOf(data[7]));
            game.setJpSales(Double.valueOf(data[8]));
            game.setOtherSales(Double.valueOf(data[9]));
            game.setGlobalSales(Double.valueOf(data[10]));
            return game;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            logger.info("Ошибка в добавлении записи: " + game.toString());
            return null;
        }
    }


    public void secondTask() {
        double max_sales = Double.MIN_VALUE;
        String query = "SELECT * FROM games WHERE year_game = 2000";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            Game game = new Game();
            while (rs.next()) {
                if (rs.getDouble("eu_sales") > max_sales) {
                    max_sales = rs.getDouble("eu_sales");
                    getNewGame(rs, game);
                }
            }
            logger.info("Запрос второго задания отработал корректно.");
            Thread.sleep(100);
            System.out.println("Второе задание: " + game.toString());
        } catch (SQLException | InterruptedException e) {
            logger.info("Ошибка в поиске максимального значения!");
        }
    }

    public void thirdTask() {
        double max_value = Double.MIN_VALUE;
        String query = "SELECT * FROM games WHERE genre = 'Sports' AND CAST(year_game AS INT) BETWEEN 2000 AND 2006 ";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            Game game = new Game();
            while (rs.next()) {
                if (rs.getDouble("jp_sales") > max_value) {
                    max_value = rs.getDouble("jp_sales");
                    getNewGame(rs, game);
                }
            }
            logger.info("Запрос третьего задания отработал корректно.");
            Thread.sleep(100);
            System.out.println("Третье задание: " + game.toString());
        } catch (SQLException | InterruptedException e) {
            logger.info("Ошибка в поиске игры из Японии.");
        }
    }

    private void getNewGame(ResultSet rs, Game game) throws SQLException {
        game.setId(rs.getInt("id"));
        game.setName(rs.getString("name_game"));
        game.setPlatform(rs.getString("platform"));
        game.setYear(String.valueOf(rs.getInt("year_game")));
        game.setGenre(rs.getString("genre"));
        game.setPublisher(rs.getString("publisher"));
        game.setNaSales(rs.getDouble("na_sales"));
        game.setEuSales(rs.getDouble("eu_sales"));
        game.setJpSales(rs.getDouble("jp_sales"));
        game.setOtherSales(rs.getDouble("other_sales"));
        game.setGlobalSales(rs.getDouble("global_sales"));
    }

    public void closeConnection() {
        try {
            connection.close();
            logger.info("Соединение закрыто");
        } catch (SQLException e) {
            System.exit(0);
        }
    }

    public void firstTask() {
        Map<String, Double> map = new HashMap<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT platform as 'platform', AVG(global_sales) as 'global_sales' " +
                    "FROM games " +
                    "GROUP BY platform");
            while (rs.next()) {
                map.put(rs.getString("platform"), rs.getDouble("global_sales"));
            }
            statement.close();
            logger.info("Данные для графика успешно подгружены.");
        } catch (SQLException e) {
            logger.info("Ошибка в построении графика.");
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (var entry : map.entrySet()) {
            dataset.addValue(entry.getValue(), entry.getKey(), "Платформа");
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "Средние показатели глобальных продаж по платформам",
                "Платформа",
                "Средние показатели глобальных продаж по платформам",
                dataset);
        chart.setBackgroundPaint(Color.white);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        JFrame frame =
                new JFrame("Первое задание");
        frame.getContentPane()
                .add(new ChartPanel(chart));
        frame.setSize(1000, 800);
        frame.setVisible(true);
    }
}
