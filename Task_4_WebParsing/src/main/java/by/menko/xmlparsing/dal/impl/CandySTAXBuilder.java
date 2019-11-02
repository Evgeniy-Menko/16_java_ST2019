package by.menko.xmlparsing.dal.impl;

import by.menko.xmlparsing.bean.Candy;
import by.menko.xmlparsing.bean.CandyType;
import by.menko.xmlparsing.bean.Type;
import by.menko.xmlparsing.bean.Unit;
import by.menko.xmlparsing.dal.Spetification;
import by.menko.xmlparsing.dal.XmlEnum;


import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;
import java.util.ArrayList;
import java.util.List;

public class CandySTAXBuilder implements Spetification {
    private List<Candy> candies;
    private XMLInputFactory factory;

    public CandySTAXBuilder() {
        factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
    }

    public List<Candy> getCandies() {
        return candies;
    }

    public void buildSetCandies(String fileName) throws XMLStreamException {

        Candy candy = null;
        Type type = null;
        Unit unit = null;

        // current element name holder
        String currentElement = null;
        XMLEventReader reader = null;
        try {
            boolean flagQuality = false;
            reader = factory.createXMLEventReader(new StreamSource(fileName));
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                // skip any empty content
                if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
                    continue;
                }

                // handler for start tags
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    currentElement = startElement.getName().getLocalPart();

                    if (XmlEnum.INGREDIENTS.value().equals(currentElement)
                            || XmlEnum.VALUE.value().equals(currentElement)) {
                        flagQuality = true;
                        continue;
                    } else if (XmlEnum.CANDIES.value().equals(currentElement)) {
                        candies = new ArrayList<>();
                        continue;
                    } else if (XmlEnum.TYPE.value().equals(currentElement)) {
                        type = getType(startElement);
                        continue;
                    } else if (XmlEnum.CANDY.value().equals(currentElement)) {
                        candy = new Candy();
                        candy.setId(Integer.parseInt(startElement
                                .getAttributeByName(new QName(XmlEnum.ID.value())).getValue()));
                        continue;
                    } else if ((flagQuality || XmlEnum.ENERGY.value().equals(currentElement))
                            && !XmlEnum.CHOCOLATE_TYPE.value().equals(currentElement)
                            && !XmlEnum.TASTE.value().equals(currentElement)) {
                        unit = getQuality(startElement);
                        continue;
                    }

                }

                // handler for contents
                if (event.isCharacters()) {
                    Characters characters = event.asCharacters();

                    if (XmlEnum.NAME.value().equals(currentElement)) {
                        candy.setName(characters.getData());
                        continue;
                    } else if ((flagQuality || XmlEnum.ENERGY.value().equals(currentElement))
                            && !XmlEnum.CHOCOLATE_TYPE.value().equals(currentElement)
                            && !XmlEnum.TASTE.value().equals(currentElement)) {
                        unit.setCount(Double.parseDouble(characters.getData()));
                        continue;
                    } else if (XmlEnum.CHOCOLATE_TYPE.value().equals(currentElement)) {
                        type.setTypeChocolate(characters.getData());
                        continue;
                    } else if (XmlEnum.TASTE.value().equals(currentElement)) {
                        type.setTasteCaramel(characters.getData());
                        continue;
                    } else if (XmlEnum.PRODUCTION.value().equals(currentElement)) {
                        candy.setProduction(characters.getData());
                        continue;
                    } else if (XmlEnum.PRODUCTION_DATE.value().equals(currentElement)) {
                        candy.setProductionDate(characters.getData());
                        continue;
                    }
                }

                // handler for end tags
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    String localName = endElement.getName().getLocalPart();

                    if (XmlEnum.CANDY.value().equals(localName)) {
                        candies.add(candy);

                    } else if (XmlEnum.ENERGY.value().equals(localName)) {
                        candy.setEnergy(unit);

                    } else if (XmlEnum.WATER.value().equals(localName) || XmlEnum.SUGAR.value().equals(localName)
                            || XmlEnum.FRUCTOSE.value().equals(localName)
                            || XmlEnum.VANILIN.value().equals(localName)) {
                        type.getIngredients().add(unit);

                    } else if (XmlEnum.PROTEINS.value().equals(localName) || XmlEnum.FATS.value().equals(localName)
                            || XmlEnum.CARBOHYDRATES.value().equals(localName)) {
                        type.getValues().add(unit);

                    } else if (XmlEnum.TYPE.value().equals(localName)) {
                        candy.setType(type);

                    } else if (XmlEnum.INGREDIENTS.value().equals(localName)
                            || XmlEnum.VALUE.value().equals(localName)) {
                        flagQuality = false;

                    }
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }


    }

    private Unit getQuality(final StartElement element) {
        Unit temp = new Unit();
        temp.setName(element.getName().getLocalPart());
        temp.setCi(element
                .getAttributeByName(new QName(XmlEnum.CI.value()))
                .getValue());
        return temp;
    }

    private Type getType(StartElement startElement) {
        Type type;
        String typeCandy = startElement.getAttributeByName(
                new QName("http://www.w3.org/2001/XMLSchema-instance",
                        XmlEnum.TYPE.value())).getValue();
        String filling = startElement.getAttributeByName(new QName(XmlEnum.FILLING.value())).getValue();
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