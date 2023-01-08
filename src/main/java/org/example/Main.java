package org.example;

import org.example.Data.GameController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите аболютный путь до файла: ");
        //Игры.csv
        String path = scanner.nextLine();
        System.out.println("Таблица уже была создана? (да/нет)");
        String choose = scanner.nextLine();
        GameController gameController;
        if (Objects.equals(choose.toLowerCase(), "да")) {
            gameController = new GameController(getConnection(), path, true);
            gameController.firstTask();
            gameController.secondTask();
            gameController.thirdTask();
            gameController.closeConnection();
        } else if (Objects.equals(choose.toLowerCase(), "нет")) {
            gameController = new GameController(getConnection(), path, false);
            gameController.firstTask();
            gameController.secondTask();
            gameController.thirdTask();
            gameController.closeConnection();
        } else System.exit(0);

    }

    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:game.db");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Я тут");
            return null;
        }
    }

}