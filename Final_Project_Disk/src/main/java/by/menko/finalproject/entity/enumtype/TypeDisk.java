package by.menko.finalproject.entity.enumtype;

public enum TypeDisk {
    FILM("film"),
    GAME("game"),
    MUSIC("music");

    private String name;

    private TypeDisk(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}