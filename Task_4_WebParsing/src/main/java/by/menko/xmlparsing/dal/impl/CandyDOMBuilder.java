package by.menko.xmlparsing.dal.impl;

import by.menko.xmlparsing.bean.Candy;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import by.menko.xmlparsing.bean.CandyType;
import by.menko.xmlparsing.bean.Type;
import by.menko.xmlparsing.bean.Unit;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CandyDOMBuilder {
    private List<Candy> candies;
    private DocumentBuilder docBuilder;


    public CandyDOMBuilder() {
        this.candies = new ArrayList<Candy>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Ошибка конфигурации парсера: " + e);
        }
    }

    public List<Candy> getCandies() {
        return candies;
    }

    public void buildSetCandies(String fileName) {
        Document doc = null;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList candiesList = root.getElementsByTagName("candy");
            for (int i = 0; i < candiesList.getLength(); i++) {
                Element candyElement = (Element) candiesList.item(i);
                Candy candy = buildCandy(candyElement);
                candies.add(candy);
            }
        } catch (IOException e) {
            System.err.println("File error or I/O error: " + e);
        } catch (SAXException e) {
            System.err.println("Parsing failure: " + e);
        }
    }

    private Candy buildCandy(Element candyElement) {
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

        return candy;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }

    private static Unit getUnitContext(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        Unit unit = new Unit();
        unit.setName(elementName);
        node.getAttributes();
        unit.setCi(node.getAttributes().item(0).getTextContent());
        unit.setCount(Double.parseDouble(node.getTextContent()));
        return unit;
    }


    private static Type getType(Element candyElement) {
        Type type;
        NodeList nList = candyElement.getElementsByTagName("type");
        Node node = nList.item(0);
        String typeCandy = node.getAttributes().item(1).getTextContent();
        String filling = node.getAttributes().item(0).getTextContent();
        if (CandyType.CARAMEL.toString().toLowerCase().equals(typeCandy)) {
            type = new Type(CandyType.CARAMEL);
            type.setTasteCaramel(getElementTextContent(candyElement, "taste"));
        } else if (CandyType.CHOCOLATE.toString().toLowerCase().equals(typeCandy)) {
            type = new Type(CandyType.CHOCOLATE);
            type.setTypeChocolate(getElementTextContent(candyElement, "chocolateType"));
        } else {
            type = new Type(CandyType.IRIS);
        }
        type.getCandyTypes().setFilling(Boolean.parseBoolean(filling));
        return type;
    }
}
