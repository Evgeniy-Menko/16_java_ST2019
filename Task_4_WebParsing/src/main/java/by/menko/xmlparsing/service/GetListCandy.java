package by.menko.xmlparsing.service;

import by.menko.xmlparsing.bean.Candy;
import by.menko.xmlparsing.dal.Spetification;
import by.menko.xmlparsing.dal.factory.CandyBuilderFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public class GetListCandy {
    public List<Candy> getListCandy(String paramParse, String fileName) throws XMLStreamException, IOException, SAXException, ParserConfigurationException {
        Spetification spetification = new CandyBuilderFactory()
                .createStudentBuilder(paramParse);
        spetification.buildSetCandies(fileName);
        return spetification.getCandies();
    }
}
