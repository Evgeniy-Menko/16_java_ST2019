package by.menko.xmlparsing.dal.impl;

import by.menko.xmlparsing.bean.Candy;
import by.menko.xmlparsing.dal.CandyHandler;
import by.menko.xmlparsing.dal.Spetification;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class CandySaxBuilder implements Spetification {

    private List<Candy> candies;
    private CandyHandler sh;
    private XMLReader reader;

    public CandySaxBuilder() throws SAXException {
        sh = new CandyHandler();

        reader = XMLReaderFactory.createXMLReader();
        reader.setContentHandler(sh);
    }

    public List<Candy> getCandies() {
        return candies;
    }

    public void buildSetCandies(String fileName) throws IOException, SAXException {
        reader.parse(fileName);
        candies = sh.getCandies();

    }
}

