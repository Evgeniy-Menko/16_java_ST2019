package by.menko.finalproject.entity;

import by.menko.finalproject.entity.enumtype.TypeConsole;

public class Game extends Disk {
    private TypeConsole typeConsole;
    private int ageLimit;
    private String developer;

    public TypeConsole getTypeConsole() {
        return typeConsole;
    }

    public Game setTypeConsole(TypeConsole typeConsole) {
        this.typeConsole = typeConsole;
        return this;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public Game setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
        return this;
    }

    public String getDeveloper() {
        return developer;
    }

    public Game setDeveloper(String developer) {
        this.developer = developer;
        return this;
    }
}
