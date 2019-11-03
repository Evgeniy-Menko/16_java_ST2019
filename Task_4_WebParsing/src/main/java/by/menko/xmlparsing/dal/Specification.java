package by.menko.xmlparsing.dal;

import by.menko.xmlparsing.bean.Candy;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public interface Specification {
    /**
     * Parse.
     *
     * @param fileName file for parsing.
     *
     * @throws XMLStreamException .
     * @throws IOException        .
     * @throws SAXException       .
     */
    void buildSetCandies(String fileName) throws XMLStreamException,
            IOException, SAXException;

    /**
     * get all candies.
     *
     * @return list candy.
     */
    List<Candy> getCandies();
}
