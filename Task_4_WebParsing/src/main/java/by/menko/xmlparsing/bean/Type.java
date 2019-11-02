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

    private String tasteCaramel;

    public String getTasteCaramel() {
        if (candyTypes.getName().equals("caramel")) {
            return tasteCaramel;
        } else {
            return "---";
        }
    }

    public Type setTasteCaramel(String tasteCaramel) {
        this.tasteCaramel = tasteCaramel;
        return this;
    }

    public CandyType getCandyType() {
        return candyTypes;
    }

    public Type(CandyType candyType) {
        this.candyTypes = candyType;
    }

    /**
     * Get Ingredients.
     *
     * @return the {@link Type#ingredients}.
     */
    public List<Unit> getIngredients() {
        if (ingredients == null) {
            ingredients = new ArrayList<Unit>();
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
            values = new ArrayList<Unit>();
        }
        return values;
    }

    public String getTypeChocolate() {
        if (candyTypes.getName().equals("chocolate")) {
            return typeChocolate;
        } else {
            return "---";
        }
    }

    public void setTypeChocolate(String typeChocolate) {
        this.typeChocolate = typeChocolate;
    }

    public CandyType getCandyTypes() {
        return candyTypes;
    }

    public Type setCandyTypes(CandyType candyTypes) {
        this.candyTypes = candyTypes;
        return this;
    }
}
