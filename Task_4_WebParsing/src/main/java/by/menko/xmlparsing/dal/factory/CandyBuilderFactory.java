package by.menko.xmlparsing.dal.factory;

import by.menko.xmlparsing.dal.Spetification;
import by.menko.xmlparsing.dal.impl.CandyDOMBuilder;
import by.menko.xmlparsing.dal.impl.CandySTAXBuilder;
import by.menko.xmlparsing.dal.impl.CandySaxBuilder;

public class CandyBuilderFactory {

    private enum TypeParser {SAX, STAX, DOM}

    public Spetification createStudentBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new CandyDOMBuilder();
            case STAX:
                return new CandySTAXBuilder();
            case SAX:
                return new CandySaxBuilder();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }

}
