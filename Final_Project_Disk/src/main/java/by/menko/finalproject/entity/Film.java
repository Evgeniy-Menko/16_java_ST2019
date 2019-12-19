package by.menko.finalproject.entity;

public class Film extends Disk {
    /**
     * Country.
     */
    private String country;
    /**
     * running time.
     */
    private String runningTime;

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(final String runningTime) {
        this.runningTime = runningTime;
    }

    @Override
    public boolean equals(final Object o) {
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
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (runningTime != null ? runningTime.hashCode() : 0);
        return result;
    }
}
