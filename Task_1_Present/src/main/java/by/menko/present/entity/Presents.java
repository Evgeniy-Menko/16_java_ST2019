package by.menko.present.entity;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Evgeniy Menko
 */
public class Presents {
    /**
     * Sweet's id.
     */
    private static int id = 0;
    /**
     * Customer's name for present.
     */
    private String nameCustomer;
    /**
     * Storage for sweets.
     */
    private Map<Integer, Sweets> sweets = new HashMap<Integer, Sweets>();

    /**
     * Getter for map sweets.
     *
     * @return map.
     */
    public Map<Integer, Sweets> getSweets() {
        return sweets;
    }

    /**
     * Set for map sweets.
     *
     * @param sweet map with object sweets.
     */
    public void setSweets(final Map<Integer, Sweets> sweet) {
        this.sweets = sweet;
    }

    /**
     * Constructor for Presents.
     *
     * @param name customer's name;
     */
    public Presents(final String name) {
        this.nameCustomer = name;
    }

    /**
     * Getter for customer's name.
     *
     * @return .
     */
    public String getNameCustomer() {
        return nameCustomer;
    }

    /**
     * Set for customer's name.
     *
     * @param name customer.
     */
    public void setNameCustomer(final String name) {
        this.nameCustomer = name;
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
        Presents presents = (Presents) o;
        return Objects.equals(nameCustomer, presents.nameCustomer);
    }

    /**
     * Hashcode.
     *
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nameCustomer);
    }

    /**
     * toString.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return nameCustomer + "\n"
                + sweets.values() + "\n";
    }

    /**
     * Print object.
     *
     * @return string.
     */
    public String print() {
        return "Name customer = " + nameCustomer + "\n"
                + sweets.values() + "\n";
    }

    /**
     * Add sweets to the present.
     *
     * @param sweet object Sweets.
     */
    public void addSweet(final Sweets sweet) {
        this.sweets.put(id++, sweet);
    }
}
