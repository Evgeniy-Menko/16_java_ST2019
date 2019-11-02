package by.menko.xmlparsing.bean;

public class Candy {
    /**
     * Id.
     */
    private int id;
    /**
     * Name.
     */
    private String name;
    /**
     * Energy.
     */
    private Unit energy;
    /**
     * Production.
     */
    private String production;
    /**
     * Type.
     */
    private Type type;
    /**
     * production date.
     */
    private String productionDate;

    /**
     * @return
     */
    public String getProductionDate() {
        return productionDate;
    }

    /**
     * @param productionDate .
     *
     * @return
     */
    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    /**
     * @return the energy.
     *
     * @Getter {@link Candy#energy}.
     */
    public final Unit getEnergy() {
        return energy;
    }

    /**
     * Setter {@link Candy#energy}.
     *
     * @param energy the energy to set.
     */
    public final void setEnergy(Unit energy) {
        this.energy = energy;
    }

    /**
     * Getter {@link Candy#name}.
     *
     * @return the name.
     */
    public final String getName() {
        return name;
    }

    /**
     * Setter {@link Candy#name}.
     *
     * @param name the name to set.
     */
    public final void setName(String name) {
        this.name = name;
    }

    /**
     * Getter {@link Candy#production}.
     *
     * @return the production.
     */
    public final String getProduction() {
        return production;
    }

    /**
     * Setter {@link Candy#production}.
     *
     * @param production the production to set.
     */
    public final void setProduction(String production) {
        this.production = production;
    }

    /**
     * Setter {@link Candy#id}.
     *
     * @return the id.
     */
    public final int getId() {
        return id;
    }

    /**
     * Setter {@link Candy#id}.
     *
     * @param id the id to set.
     */
    public final void setId(int id) {
        this.id = id;
    }

    /**
     * Getter {@link Candy#type}.
     *
     * @return the type.
     */
    public final Type getType() {
        return type;
    }

    /**
     * Setter {@link Candy#type}.
     *
     * @param type the type to set.
     */
    public final void setType(Type type) {
        this.type = type;
    }

}
