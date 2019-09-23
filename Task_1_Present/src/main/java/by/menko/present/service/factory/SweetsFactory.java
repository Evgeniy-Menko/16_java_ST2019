package by.menko.present.service.factory;

import by.menko.present.entity.Chocolate;
import by.menko.present.entity.Cookie;
import by.menko.present.entity.Marshmallows;
import by.menko.present.entity.Sweets;

/**
 * @author Evgeniy Menko
 */
public class SweetsFactory {
    /**
     * Index sweet's type.
     */
    private final int indexType = 0;
    /**
     * Index sweet's name.
     */
    private final int indexName = 1;
    /**
     * Index sweet's weight.
     */
    private final int indexWeight = 2;
    /**
     * Index sweet's sugar.
     */
    private final int indexSugar = 3;
    /**
     * Index sweet's cost.
     */
    private final int indexCost = 4;
    /**
     * Index sweet's another parameter.
     */
    private final int indexAnotherParam = 5;
    /**
     * length String array for object sweet.
     */
    private final int lengtParametrs = 6;

    /**
     * This method return object class(Chocolate,Cookie,Marshmallows).
     *
     * @param param String array with parameter.
     *
     * @return object.
     */
    public Sweets getSweets(final String[] param) {
        String type = param[indexType];
        Sweets toReturn = null;
        switch (type) {
            case "chocolate":
                toReturn = new Chocolate(param[indexName],
                        Integer.parseInt(param[indexWeight]),
                        Integer.parseInt(param[indexSugar]),
                        Integer.parseInt(param[indexCost]),
                        param[indexAnotherParam]);
                break;
            case "cookie":
                toReturn = new Cookie(param[indexName],
                        Integer.parseInt(param[indexWeight]),
                        Integer.parseInt(param[indexSugar]),
                        Integer.parseInt(param[indexCost]),
                        param[indexAnotherParam]);
                break;
            case "marshmallows":
                toReturn = new Marshmallows(param[indexName],
                        Integer.parseInt(param[indexWeight]),
                        Integer.parseInt(param[indexSugar]),
                        Integer.parseInt(param[indexCost]),
                        param[indexAnotherParam]);
                break;
            default:
        }
        return toReturn;
    }
}
