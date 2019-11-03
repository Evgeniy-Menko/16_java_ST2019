package by.menko.xmlparsing.dal.impl;

import by.menko.xmlparsing.bean.Candy;
import by.menko.xmlparsing.dal.CandyHandler;
import by.menko.xmlparsing.dal.Specification;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class CandySaxBuilder implements Specification {
    /**
     * List candies.
     */
    private List<Candy> candies;
    /**
     * object CandyHandler.
     */
    private CandyHandler sh;
    /**
     * XML reader.
     */
    private XMLReader reader;

    /**
     * Constructor.
     *
     * @throws SAXException .
     */
    public CandySaxBuilder() throws SAXException {
        sh = new CandyHandler();

        reader = XMLReaderFactory.createXMLReader();
        reader.setContentHandler(sh);
    }

    /**
     * getter candies.
     *
     * @return list candies.
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
        reader.parse(fileName);
        candies = sh.getCandies();

    }
}

