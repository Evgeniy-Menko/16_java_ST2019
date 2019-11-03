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
     * @return production date.
     */
    public String getProductionDate() {
        return productionDate;
    }

    /**
     * @param productionDateCandy .
     */
    public void setProductionDate(final String productionDateCandy) {
        this.productionDate = productionDateCandy;
    }

    /**
     * @return the energy.
     *
     * @Getter energy.
     */
    public final Unit getEnergy() {
        return energy;
    }

    /**
     * Setter energy.
     *
     * @param energyCandy the energy to set.
     */
    public final void setEnergy(final Unit energyCandy) {
        this.energy = energyCandy;
    }

    /**
     * Getter name.
     *
     * @return the name.
     */
    public final String getName() {
        return name;
    }

    /**
     * Setter name.
     *
     * @param nameCandy the name to set.
     */
    public final void setName(final String nameCandy) {
        this.name = nameCandy;
    }

    /**
     * Getter production.
     *
     * @return the production.
     */
    public final String getProduction() {
        return production;
    }

    /**
     * Setter production.
     *
     * @param productionCandy the production to set.
     */
    public final void setProduction(final String productionCandy) {
        this.production = productionCandy;
    }

    /**
     * Setter id.
     *
     * @return the id.
     */
    public final int getId() {
        return id;
    }

    /**
     * Setter id}.
     *
     * @param idCandy the id to set.
     */
    public final void setId(final int idCandy) {
        this.id = idCandy;
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
     * Setter type.
     *
     * @param typeCandy the type to set.
     */
    public final void setType(final Type typeCandy) {
        this.type = typeCandy;
    }

}
