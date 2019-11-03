package by.menko.xmlparsing.dal.impl;

import by.menko.xmlparsing.bean.Candy;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import by.menko.xmlparsing.bean.CandyType;
import by.menko.xmlparsing.bean.Type;
import by.menko.xmlparsing.bean.Unit;
import by.menko.xmlparsing.dal.Specification;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CandyDOMBuilder implements Specification {
    /**
     * list candies.
     */
    private List<Candy> candies;
    /**
     * document builder.
     */
    private DocumentBuilder docBuilder;

    /**
     * Constructor.
     *
     * @throws ParserConfigurationException .
     */
    public CandyDOMBuilder() throws ParserConfigurationException {
        this.candies = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        docBuilder = factory.newDocumentBuilder();
    }

    /**
     * Getter candies.
     *
     * @return list candy.
     */
    public List<Candy> getCandies() {
        return candies;
    }

    /**
     * parse file.
     *
     * @param fileName file for parsing.
     *
     * @throws IOException  .
     * @throws SAXException .
     */
    public void buildSetCandies(final String fileName)
            throws IOException, SAXException {
        Document doc = null;

        doc = docBuilder.parse(fileName);
        Element root = doc.getDocumentElement();
        NodeList candiesList = root.getElementsByTagName("candy");
        for (int i = 0; i < candiesList.getLength(); i++) {
            Element candyElement = (Element) candiesList.item(i);
            Candy candy = buildCandy(candyElement);
            candies.add(candy);
        }


    }

    /**
     * Builder candy.
     *
     * @param candyElement .
     *
     * @return candy.
     */
    private Candy buildCandy(final Element candyElement) {
        Candy candy = new Candy();
        Type type;
        candy.setId(Integer.parseInt(candyElement.getAttribute("id")));
        candy.setName(getElementTextContent(candyElement, "name"));
        candy.setEnergy(getUnitContext(candyElement, "energy"));
        type = getType(candyElement);
        type.getIngredients().add(getUnitContext(candyElement, "water"));
        type.getIngredients().add(getUnitContext(candyElement, "sugar"));
        type.getIngredients().add(getUnitContext(candyElement, "fructose"));
        type.getIngredients().add(getUnitContext(candyElement, "vanillin"));
        type.getValues().add(getUnitContext(candyElement, "proteins"));
        type.getValues().add(getUnitContext(candyElement, "fats"));
        type.getValues().add(getUnitContext(candyElement, "carbohydrates"));
        candy.setType(type);
        candy.setProduction(getElementTextContent(candyElement, "production"));
        candy.setProductionDate(getElementTextContent(candyElement,
                "productionDate"));

        return candy;
    }

    /**
     * get content.
     *
     * @param element     .
     * @param elementName .
     *
     * @return content.
     */
    private static String getElementTextContent(
            final Element element, final String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }

    /**
     * get unit.
     *
     * @param element     .
     * @param elementName .
     *
     * @return unit.
     */
    private static Unit getUnitContext(final Element element,
                                       final String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        Element elementUnit = (Element) nList.item(0);
        Unit unit = new Unit();
        unit.setName(elementName);
        unit.setCi(elementUnit.getAttribute("ci"));
        unit.setCount(Double.parseDouble(node.getTextContent()));
        return unit;
    }

    /**
     * get type.
     *
     * @param candyElement .
     *
     * @return type.
     */
    private static Type getType(final Element candyElement) {
        Type type;
        NodeList nList = candyElement.getElementsByTagName("type");
        Element elementType = (Element) nList.item(0);
        String typeCandy = elementType.getAttribute("xsi:type");
        String filling = elementType.getAttribute("filling");
        if (CandyType.CARAMEL.toString().toLowerCase().equals(typeCandy)) {
            type = new Type(CandyType.CARAMEL);
            type.setTasteCaramel(getElementTextContent(candyElement, "taste"));
        } else if (CandyType.CHOCOLATE.toString().toLowerCase()
                .equals(typeCandy)) {
            type = new Type(CandyType.CHOCOLATE);
            type.setTypeChocolate(getElementTextContent(candyElement,
                    "chocolateType"));
        } else {
            type = new Type(CandyType.IRIS);
        }
        type.getCandyTypes().setFilling(Boolean.parseBoolean(filling));
        return type;
    }
}
