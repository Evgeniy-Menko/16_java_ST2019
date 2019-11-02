package by.menko.xmlparsing.dal;

import by.menko.xmlparsing.bean.Candy;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public interface Spetification {
    void buildSetCandies(String fileName) throws XMLStreamException, IOException, SAXException;

    List<Candy> getCandies();
}
