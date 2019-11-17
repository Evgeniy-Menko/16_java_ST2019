package by.menko.finalproject.entity;


import by.menko.finalproject.entity.enumtype.TypeDisk;

import java.io.File;

import java.util.Date;

public class Disk extends Entity {
    private Integer idUser;
    private String nameDisk;
    private String genre;
    private Double price;
    private TypeDisk type;
    private File image;
    private String description;
    private Date year;
    private Date timeAdded;
    private Boolean flagBlocked;

    public Integer getIdUser() {
        return idUser;
    }

    public Disk setIdUser(Integer idUser) {
        this.idUser = idUser;
        return this;
    }

    public String getNameDisk() {
        return nameDisk;
    }

    public Disk setNameDisk(String nameDisk) {
        this.nameDisk = nameDisk;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public Disk setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Disk setPrice(Double price) {
        this.price = price;
        return this;
    }

    public TypeDisk getType() {
        return type;
    }

    public Disk setType(TypeDisk type) {
        this.type = type;
        return this;
    }

    public File getImage() {
        return image;
    }

    public Disk setImage(File image) {
        this.image = image;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Disk setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getYear() {
        return year;
    }

    public Disk setYear(Date year) {
        this.year = year;
        return this;
    }

    public Date getTimeAdded() {
        return timeAdded;
    }

    public Disk setTimeAdded(Date timeAdded) {
        this.timeAdded = timeAdded;
        return this;
    }

    public Boolean getFlagBlocked() {
        return flagBlocked;
    }

    public Disk setFlagBlocked(Boolean flagBlocked) {
        this.flagBlocked = flagBlocked;
        return this;
    }
}
