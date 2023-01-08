package org.example.Data;

public class Game {
    private Integer id;
    private String name;
    private String platform;
    private String year;
    private String genre;
    private String publisher;
    private Double naSales;
    private Double euSales;
    private Double jpSales;
    private Double otherSales;
    private Double globalSales;

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", platform='" + platform + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                ", publisher='" + publisher + '\'' +
                ", naSales='" + naSales + '\'' +
                ", eaSales='" + euSales + '\'' +
                ", jpSales='" + jpSales + '\'' +
                ", otherSales='" + otherSales + '\'' +
                ", globalSales='" + globalSales + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Double getNaSales() {
        return naSales;
    }

    public void setNaSales(Double naSales) {
        this.naSales = naSales;
    }

    public Double getEuSales() {
        return euSales;
    }

    public void setEuSales(Double euSales) {
        this.euSales = euSales;
    }

    public Double getJpSales() {
        return jpSales;
    }

    public void setJpSales(Double jpSales) {
        this.jpSales = jpSales;
    }

    public Double getOtherSales() {
        return otherSales;
    }

    public void setOtherSales(Double otherSales) {
        this.otherSales = otherSales;
    }

    public Double getGlobalSales() {
        return globalSales;
    }

    public void setGlobalSales(Double globalSales) {
        this.globalSales = globalSales;
    }
}
