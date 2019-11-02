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

    private List<Candy> candies;
    private Candy candy;
    private Type type;
    private Unit unit;
    private boolean flagQuality = false;
    private String currentElement;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentElement = localName;
        if (XmlEnum.INGREDIENTS.value().equals(currentElement) || XmlEnum.VALUE.value().equals(currentElement)) {
            flagQuality = true;
        } else if (XmlEnum.CANDIES.value().equals(currentElement)) {
            candies = new ArrayList<Candy>();
        } else if (XmlEnum.CANDY.value().equals(currentElement)) {
            candy = new Candy();
            candy.setId(Integer.parseInt(attributes.getValue(XmlEnum.ID.value())));
        } else if (XmlEnum.TYPE.value().equals(currentElement)) {
            type = getType(attributes);
        } else if ((flagQuality || XmlEnum.ENERGY.value().equals(currentElement))
                && !XmlEnum.CHOCOLATE_TYPE.value().equals(currentElement)
                && !XmlEnum.TASTE.value().equals(currentElement)) {
            unit = getUnit(currentElement, attributes, uri);
        }

    }

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
    public void characters(char[] ch, final int start, final int length) {
        String elementText = new String(ch, start, length).trim();

        if (elementText.isEmpty()) {
            return;
        } else if (XmlEnum.NAME.value().equals(currentElement)) {
            candy.setName(elementText);
        } else if ((flagQuality || XmlEnum.ENERGY.value().equals(currentElement))
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
    public void endElement(final String uri, final String localName, final String qName) {

        if (XmlEnum.CANDY.value().equals(localName)) {
            candies.add(candy);
        } else if (XmlEnum.ENERGY.value().equals(localName)) {
            candy.setEnergy(unit);
        } else if (XmlEnum.WATER.value().equals(localName) || XmlEnum.SUGAR.value().equals(localName)
                || XmlEnum.FRUCTOSE.value().equals(localName) || XmlEnum.VANILIN.value().equals(localName)) {
            type.getIngredients().add(unit);
        } else if (XmlEnum.PROTEINS.value().equals(localName) || XmlEnum.FATS.value().equals(localName)
                || XmlEnum.CARBOHYDRATES.value().equals(localName)) {
            type.getValues().add(unit);
        } else if (XmlEnum.TYPE.value().equals(localName)) {
            candy.setType(type);
        } else if (XmlEnum.INGREDIENTS.value().equals(localName) || XmlEnum.VALUE.value().equals(localName)) {
            flagQuality = false;
        }
    }

    private Unit getUnit(final String name, final Attributes attr, final String uri) {
        Unit temp = new Unit();
        temp.setName(name);
        temp.setCi(attr.getValue(XmlEnum.CI.value()));
        return temp;
    }

    private Type getType(Attributes attributes) {
        Type type;
        String typeCandy = attributes.getValue("http://www.w3.org/2001/XMLSchema-instance", XmlEnum.TYPE.value());
        String filling = attributes.getValue(XmlEnum.FILLING.value());
        if (CandyType.CARAMEL.toString().toLowerCase().equals(typeCandy)) {
            type = new Type(CandyType.CARAMEL);
        } else if (CandyType.CHOCOLATE.toString().toLowerCase().equals(typeCandy)) {
            type = new Type(CandyType.CHOCOLATE);
        } else {
            type = new Type(CandyType.IRIS);
        }
        type.getCandyTypes().setFilling(Boolean.parseBoolean(filling));
        return type;
    }
}
