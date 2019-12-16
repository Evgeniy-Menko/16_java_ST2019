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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;

    }


    public Timestamp getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Timestamp timeAdded) {
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
        result = 31 * result + idUser.hashCode();
        result = 31 * result + nameDisk.hashCode();
        result = 31 * result + genre.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + image.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + year;
        result = 31 * result + timeAdded.hashCode();
        result = 31 * result + flagBlocked.hashCode();
        return result;
    }
}
