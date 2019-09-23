package by.menko.present.entity;

import java.util.Objects;

/**
 * @author Evgeniy Menko
 */
public class Sweets {
    /**
     * Sweet's type.
     */
    private String type;
    /**
     * Sweet's name.
     */
    private String name;
    /**
     * Sweet's weight.
     */
    private int weight;
    /**
     * Sweet's sugar.
     */
    private int sugar;
    /**
     * Sweet's cost.
     */
    private int cost;

    /**
     * Constructor for Sweets.
     *
     * @param typeSweet   sweet's type.
     * @param nameSweet   sweet's name.
     * @param weightSweet sweet's weight.
     * @param sugarSweet  sweet's sugar.
     * @param costSweet   sweet's cost.
     */
    public Sweets(final String typeSweet, final String nameSweet,
                  final int weightSweet,
                  final int sugarSweet, final int costSweet) {

        this.type = typeSweet;
        this.name = nameSweet;
        this.weight = weightSweet;
        this.sugar = sugarSweet;
        this.cost = costSweet;
    }

    /**
     * Setter.
     *
     * @param weightSweet .
     */
    public void setWeight(final int weightSweet) {
        this.weight = weightSweet;
    }

    /**
     * Getter.
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter.
     *
     * @param typeSweet .
     */
    public void setType(final String typeSweet) {
        this.type = typeSweet;
    }

    /**
     * Getter.
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter.
     *
     * @param nameSweet .
     */
    public void setName(final String nameSweet) {
        this.name = nameSweet;
    }

    /**
     * Getter.
     *
     * @return weight.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Setter.
     *
     * @param wieghtSweet .
     */
    public void setWieght(final int wieghtSweet) {
        this.weight = wieghtSweet;
    }

    /**
     * Getter.
     *
     * @return sugar.
     */
    public int getSugar() {
        return sugar;
    }

    /**
     * Setter.
     *
     * @param sugarSweet .
     */
    public void setSugar(final int sugarSweet) {
        this.sugar = sugarSweet;
    }

    /**
     * Getter.
     *
     * @return cost.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Setter.
     *
     * @param costSweet .
     */
    public void setCost(final int costSweet) {
        this.cost = costSweet;
    }

    /**
     * Equals.
     *
     * @param o Object.
     *
     * @return true or false.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sweets sweets = (Sweets) o;
        return weight == sweets.weight && sugar == sweets.sugar
                && cost == sweets.cost
                && Objects.equals(type, sweets.type)
                && Objects.equals(name, sweets.name);
    }

    /**
     * HashCode.
     *
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, name, weight, sugar, cost);
    }

    /**
     * toString.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "\n" + type + "," + name + ","
                + weight + "," + sugar + "," + cost;
    }

    /**
     * Print object.
     *
     * @return string.
     */
    public String print() {
        return "=id" + ", type= " + type + ", name= "
                + name + ", weight= " + weight
                + ", sugar= " + sugar + ", cost= " + cost;
    }
}
