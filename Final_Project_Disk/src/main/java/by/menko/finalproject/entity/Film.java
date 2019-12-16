package by.menko.finalproject.entity;

public class Film extends Disk {
     private  String  country;
     private String runningTime;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Film film = (Film) o;

        if (!country.equals(film.country)) return false;
        return runningTime.equals(film.runningTime);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + runningTime.hashCode();
        return result;
    }
}
