package by.menko.xmlparsing.bean;

import java.util.ArrayList;
import java.util.List;

public class Type {
    /**
     * Candy type.
     */
    private CandyType candyType;
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

}
