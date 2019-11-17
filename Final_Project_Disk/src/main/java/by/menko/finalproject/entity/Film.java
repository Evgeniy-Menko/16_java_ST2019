package by.menko.finalproject.entity;

public class Film extends Disk {
     private  String  country;
     private String runningTime;

    public String getCountry() {
        return country;
    }

    public Film setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public Film setRunningTime(String runningTime) {
        this.runningTime = runningTime;
        return this;
    }
}
