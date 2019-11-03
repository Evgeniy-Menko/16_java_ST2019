package by.menko.xmlparsing.dal;

import by.menko.xmlparsing.bean.Candy;
import by.menko.xmlparsing.bean.CandyType;
import by.menko.xmlparsing.bean.Type;
import by.menko.xmlparsing.bean.Unit;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class CandyHandler extends DefaultHandler {
    /**
     * list with candies.
     */
    private List<Candy> candies;
    /**
     * candy.
     */
    private Candy candy;
    /**
     * type.
     */
    private Type type;
    /**
     * unit.
     */
    private Unit unit;
    /**
     * flag unit.
     */
    private boolean flagUnit = false;
    /**
     * current element.
     */
    private String currentElement;

    /**
     * Start element for parse.
     *
     * @param uri        .
     * @param localName  .
     * @param qName      .
     * @param attributes .
     */
    @Override
    public void startElement(final String uri, final String localName,
                             final String qName,
                             final Attributes attributes) {
        currentElement = localName;
        if (XmlEnum.INGREDIENTS.value().equals(currentElement)
                || XmlEnum.VALUE.value().equals(currentElement)) {
            flagUnit = true;
        } else if (XmlEnum.CANDIES.value().equals(currentElement)) {
            candies = new ArrayList<>();
        } else if (XmlEnum.CANDY.value().equals(currentElement)) {
            candy = new Candy();
            candy.setId(Integer.parseInt(attributes
                    .getValue(XmlEnum.ID.value())));
        } else if (XmlEnum.TYPE.value().equals(currentElement)) {
            type = getType(attributes);
        } else if ((flagUnit || XmlEnum.ENERGY.value()
                .equals(currentElement))
                && !XmlEnum.CHOCOLATE_TYPE.value().equals(currentElement)
                && !XmlEnum.TASTE.value().equals(currentElement)) {
            unit = getUnit(currentElement, attributes);
        }

    }

    /**
     * getter for candies.
     *
     * @return list candies.
     */
    public List<Candy> getCandies() {
        return candies;
    }

    /*
     * (non-Javadoc)
     *
     * @see jdk.internal.org.xml.sax.helpers.DefaultHandler#characters(char[],
     * int, int)
     */
    @Override
    public void characters(final char[] ch, final int start, final int length) {
        String elementText = new String(ch, start, length).trim();

        if (elementText.isEmpty()) {
            return;
        } else if (XmlEnum.NAME.value().equals(currentElement)) {
            candy.setName(elementText);
        } else if ((flagUnit || XmlEnum.ENERGY.value()
                .equals(currentElement))
                && !XmlEnum.CHOCOLATE_TYPE.value().equals(currentElement)
                && !XmlEnum.TASTE.value().equals(currentElement)) {
            unit.setCount(Double.parseDouble(elementText));
        } else if (XmlEnum.CHOCOLATE_TYPE.value().equals(currentElement)) {
            type.setTypeChocolate(elementText);
        } else if (XmlEnum.PRODUCTION.value().equals(currentElement)) {
            candy.setProduction(elementText);
        } else if (XmlEnum.PRODUCTION_DATE.value().equals(currentElement)) {
            candy.setProductionDate(elementText);
        } else if (XmlEnum.TASTE.value().equals(currentElement)) {
            type.setTasteCaramel(elementText);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * jdk.internal.org.xml.sax.helpers.DefaultHandler#endElement(java.lang.
     * String, java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(final String uri, final String localName,
                           final String qName) {

        if (XmlEnum.CANDY.value().equals(localName)) {
            candies.add(candy);
        } else if (XmlEnum.ENERGY.value().equals(localName)) {
            candy.setEnergy(unit);
        } else if (XmlEnum.WATER.value().equals(localName)
                || XmlEnum.SUGAR.value().equals(localName)
                || XmlEnum.FRUCTOSE.value().equals(localName)
                || XmlEnum.VANILIN.value().equals(localName)) {
            type.getIngredients().add(unit);
        } else if (XmlEnum.PROTEINS.value().equals(localName)
                || XmlEnum.FATS.value().equals(localName)
                || XmlEnum.CARBOHYDRATES.value().equals(localName)) {
            type.getValues().add(unit);
        } else if (XmlEnum.TYPE.value().equals(localName)) {
            candy.setType(type);
        } else if (XmlEnum.INGREDIENTS.value().equals(localName)
                || XmlEnum.VALUE.value().equals(localName)) {
            flagUnit = false;
        }
    }

    private Unit getUnit(final String name, final Attributes attr) {
        Unit temp = new Unit();
        temp.setName(name);
        temp.setCi(attr.getValue(XmlEnum.CI.value()));
        return temp;
    }

    private Type getType(final Attributes attributes) {
        Type result;
        String typeCandy = attributes.
                getValue("http://www.w3.org/2001/XMLSchema-instance",
                        XmlEnum.TYPE.value());
        String filling = attributes.getValue(XmlEnum.FILLING.value());
        if (CandyType.CARAMEL.toString().toLowerCase().equals(typeCandy)) {
            result = new Type(CandyType.CARAMEL);
        } else if (CandyType.CHOCOLATE.toString()
                .toLowerCase().equals(typeCandy)) {
            result = new Type(CandyType.CHOCOLATE);
        } else {
            result = new Type(CandyType.IRIS);
        }
        result.getCandyTypes().setFilling(Boolean.parseBoolean(filling));
        return result;
    }
}
