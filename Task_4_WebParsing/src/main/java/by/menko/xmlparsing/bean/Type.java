package by.menko.xmlparsing.bean;

import java.util.ArrayList;
import java.util.List;

public class Type {
    /**
     * Candy type.
     */
    private CandyType candyTypes;
    /**
     * Ingredients.
     */
    private List<Unit> ingredients;
    /**
     * Values.
     */
    private List<Unit> values;
    /**
     * Type chocolate.
     */
    private String typeChocolate;
    /**
     * Taste caramel.
     */
    private String tasteCaramel;

    /**
     * Constructor class type.
     *
     * @param candyType .
     */
    public Type(final CandyType candyType) {
        this.candyTypes = candyType;
    }

    /**
     * Getter for taste caramel.
     *
     * @return taste if candy type equals caramel.
     */
    public String getTasteCaramel() {
        if (candyTypes.getName().equals("caramel")) {
            return tasteCaramel;
        } else {
            return "---";
        }
    }

    /**
     * Setter taste caramel.
     *
     * @param tasteCaramels .
     */
    public void setTasteCaramel(final String tasteCaramels) {
        this.tasteCaramel = tasteCaramels;
    }

    /**
     * Getter for Candy type.
     *
     * @return candy type.
     */
    public CandyType getCandyType() {
        return candyTypes;
    }


    /**
     * Get Ingredients.
     *
     * @return the {@link Type#ingredients}.
     */
    public List<Unit> getIngredients() {
        if (ingredients == null) {
            ingredients = new ArrayList<>();
        }
        return ingredients;
    }

    /**
     * Getter Values.
     *
     * @return the {@link Type#values}.
     */
    public List<Unit> getValues() {
        if (values == null) {
            values = new ArrayList<>();
        }
        return values;
    }

    /**
     * Getter type chocolate.
     *
     * @return return type chocolate if type equals chocolate.
     */
    public String getTypeChocolate() {
        if (candyTypes.getName().equals("chocolate")) {
            return typeChocolate;
        } else {
            return "---";
        }
    }

    /**
     * Setter type chocolate.
     *
     * @param typesChocolate .
     */
    public void setTypeChocolate(final String typesChocolate) {
        this.typeChocolate = typesChocolate;
    }

    /**
     * Setter candy type.
     *
     * @param candyType .
     */
    public void setCandyTypes(final CandyType candyType) {
        this.candyTypes = candyType;

    }

    /**
     * Getter for candy type.
     *
     * @return candy type.
     */
    public CandyType getCandyTypes() {
        return candyTypes;
    }
}
