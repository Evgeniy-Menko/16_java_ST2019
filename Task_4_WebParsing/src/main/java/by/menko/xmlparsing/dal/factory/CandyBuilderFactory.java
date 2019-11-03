package by.menko.xmlparsing.dal.factory;

import by.menko.xmlparsing.dal.Specification;
import by.menko.xmlparsing.dal.impl.CandyDOMBuilder;
import by.menko.xmlparsing.dal.impl.CandySTAXBuilder;
import by.menko.xmlparsing.dal.impl.CandySaxBuilder;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class CandyBuilderFactory {

    private enum TypeParser {
        /**
         * Parsers.
         */
        SAX, STAX, DOM
    }

    /**
     * Builder for specification.
     *
     * @param typeParser .
     *
     * @return specification.
     *
     * @throws ParserConfigurationException .
     * @throws SAXException                 .
     */
    public Specification createStudentBuilder(final String typeParser)
            throws ParserConfigurationException, SAXException {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new CandyDOMBuilder();
            case STAX:
                return new CandySTAXBuilder();
            case SAX:
                return new CandySaxBuilder();
            default:
                throw new EnumConstantNotPresentException(type
                        .getDeclaringClass(), type.name());
        }
    }

}
