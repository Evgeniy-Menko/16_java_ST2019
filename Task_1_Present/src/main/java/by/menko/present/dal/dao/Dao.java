package by.menko.present.dal.dao;

import by.menko.present.dal.storage.Storage;
import by.menko.present.entity.Presents;
import by.menko.present.entity.Sweets;
import by.menko.present.dal.spetification.impl.Specification;

import java.util.List;
import java.util.Map;

/**
 * @author Evgeniy Menko
 */
public final class Dao {


    /**
     * name customer.
     */
    private String nameCustomer;
    /**
     * id sweet.
     */
    private int idSweet;
    /**
     * Object storage.
     */
    private Storage storage = Storage.getInstance();


    /**
     * @param name customer
     */
    public Dao(final String name) {
        this.nameCustomer = name;
    }

    /**
     * @param id sweet
     */
    public Dao(final int id) {
        this.idSweet = id;
    }

    /**
     * Empty constructor.
     */
    public Dao() {

    }

    /**
     * @return name Customer
     */
    public String getNameCustomer() {
        return nameCustomer;
    }

    /**
     * Setter for name customer.
     *
     * @param name customer.
     */
    public void setNameCustomer(final String name) {
        this.nameCustomer = name;
    }

    /**
     * Add present for storage.
     *
     * @param presents object
     */
    public void addPresents(final Presents presents) {
        storage.getStorage().put(presents.getNameCustomer(), presents);
    }

    /**
     * Add sweet for storage.
     *
     * @param sweet object
     */
    public void addSweets(final Sweets sweet) {
        storage.getStorage().get(nameCustomer).addSweet(sweet);
    }

    /**
     * Update sweet.
     *
     * @param sweet object.
     */
    public void updateSweets(final Sweets sweet) {
        storage.getStorage().get(nameCustomer).getSweets().put(idSweet, sweet);
    }

    /**
     * Get storage.
     *
     * @return all presents.
     */
    public Map<String, Presents> getAllPresents() {
        return storage.getStorage();
    }

    /**
     * Remove present by name.
     *
     * @param name customer.
     */
    public void removePresents(final String name) {
        storage.getStorage().remove(name);
    }

    /**
     * Remove sweets by id.
     *
     * @param id   sweet.
     * @param name customer.
     */
    public void removeSweets(final int id, final String name) {
        storage.getStorage().get(name).getSweets().remove(id);
    }

    /**
     * Overrid method for specification.
     *
     * @param specification object specification.
     *
     * @return list<Sweets>.
     */
    public List<Sweets> query(final Specification specification) {
        return specification.specified(storage.getStorage());
    }
}
