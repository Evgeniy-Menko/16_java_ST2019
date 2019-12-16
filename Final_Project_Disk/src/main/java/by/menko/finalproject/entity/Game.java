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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Game game = (Game) o;

        if (ageLimit != game.ageLimit) return false;
        return developer.equals(game.developer);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + ageLimit;
        result = 31 * result + developer.hashCode();
        return result;
    }
}
