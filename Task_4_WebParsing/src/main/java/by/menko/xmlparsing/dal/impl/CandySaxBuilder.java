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

    public CandySaxBuilder() {
        sh = new CandyHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(sh);
        } catch (org.xml.sax.SAXException e) {
            e.printStackTrace();
        }

    }

    public List<Candy> getCandies() {
        return candies;
    }

    public List<Candy> buildSetCandies(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        } catch (IOException e) {
            System.err.print("ошибка I/О потока: " + e);
        }
        candies = sh.getCandies();
        return null;
    }
}

