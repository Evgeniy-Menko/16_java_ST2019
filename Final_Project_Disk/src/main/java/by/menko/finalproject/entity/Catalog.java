package by.menko.finalproject.entity;

public class Catalog {
    private int idGenre;
    private int idType;
    private String type;
    private String genre;

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(final int idGenre) {
        this.idGenre = idGenre;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(final int idType) {
        this.idType = idType;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }
}
