package by.menko.finalproject.entity;


import by.menko.finalproject.entity.enumtype.TypeDisk;

import java.io.File;

import java.util.Date;

public class Disk extends Entity {
    private Integer idUser;
    private String nameDisk;
    private String genre;
    private Double price;
    private String type;
    private File image;
    private String description;
    private Date year;
    private Date timeAdded;
    private Boolean flagBlocked;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;

    }

    public String getNameDisk() {
        return nameDisk;
    }

    public void setNameDisk(String nameDisk) {
        this.nameDisk = nameDisk;

    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;

    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;

    }


    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;

    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;

    }

    public Date getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Date timeAdded) {
        this.timeAdded = timeAdded;

    }

    public Boolean getFlagBlocked() {
        return flagBlocked;
    }

    public void setFlagBlocked(Boolean flagBlocked) {
        this.flagBlocked = flagBlocked;
    }
}
