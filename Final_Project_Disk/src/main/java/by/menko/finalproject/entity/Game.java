package by.menko.finalproject.entity;


public class Game extends Disk {

    private int ageLimit;
    private String developer;


    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;

    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;

    }
}
