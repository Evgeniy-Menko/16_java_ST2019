package by.menko.xmlparsing.service;

import by.menko.xmlparsing.bean.Candy;
import by.menko.xmlparsing.dal.Specification;
import by.menko.xmlparsing.dal.factory.CandyBuilderFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public class GetListCandy {
    /**
     * create object parse and return list candy.
     *
     * @param paramParse DOM,SAX,STAX.
     * @param fileName   file for parsing.
     *
     * @return list candy.
     *
     * @throws XMLStreamException           .
     * @throws IOException                  .
     * @throws SAXException                 .
     * @throws ParserConfigurationException .
     */
    public List<Candy> getListCandy(final String paramParse,
                                    final String fileName)
            throws XMLStreamException, IOException, SAXException,
            ParserConfigurationException {
        Specification spetification = new CandyBuilderFactory()
                .createStudentBuilder(paramParse);
        spetification.buildSetCandies(fileName);
        return spetification.getCandies();
    }
}
