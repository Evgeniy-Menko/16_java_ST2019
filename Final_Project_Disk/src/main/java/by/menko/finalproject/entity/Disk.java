package by.menko.finalproject.entity;


import java.sql.Timestamp;


public class Disk extends Entity {
    private Integer idUser;
    private String nameDisk;
    private String genre;
    private Double price;
    private String type;
    private String image;
    private String description;
    private int year;
    private Timestamp timeAdded;
    private Boolean flagBlocked;

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(final Integer idUser) {
        this.idUser = idUser;

    }

    public String getNameDisk() {
        return nameDisk;
    }

    public void setNameDisk(final String nameDisk) {
        this.nameDisk = nameDisk;

    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(final String genre) {
        this.genre = genre;

    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;

    }

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public int getYear() {
        return year;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;

    }


    public Timestamp getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(final Timestamp timeAdded) {
        this.timeAdded = timeAdded;

    }

    public Boolean getFlagBlocked() {
        return flagBlocked;
    }

    public void setFlagBlocked(Integer id) {
        if (id == 0) {
            this.flagBlocked = false;
        } else if (id == 1) {
            this.flagBlocked = true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Disk disk = (Disk) o;

        if (year != disk.year) return false;
        if (!idUser.equals(disk.idUser)) return false;
        if (!nameDisk.equals(disk.nameDisk)) return false;
        if (!genre.equals(disk.genre)) return false;
        if (!price.equals(disk.price)) return false;
        if (!type.equals(disk.type)) return false;
        if (!image.equals(disk.image)) return false;
        if (!description.equals(disk.description)) return false;
        if (!timeAdded.equals(disk.timeAdded)) return false;
        return flagBlocked.equals(disk.flagBlocked);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        result = 31 * result + (nameDisk != null ? nameDisk.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (timeAdded != null ? timeAdded.hashCode() : 0);
        result = 31 * result + (flagBlocked != null ? flagBlocked.hashCode() : 0);
        return result;
    }
}
