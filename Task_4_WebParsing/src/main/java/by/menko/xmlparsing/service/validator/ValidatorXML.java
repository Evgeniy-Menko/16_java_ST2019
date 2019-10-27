package by.menko.xmlparsing.service.validator;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ValidatorXML {
    private final String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;

    private final String schemaName = "src/main/resources/data/candies.xsd";
    private final SchemaFactory factory = SchemaFactory.newInstance(language);
    private final File schemaLocation = new File(schemaName);

    public boolean isValid(String fileName) {
        boolean result = false;
        try {       // создание схемы
            Schema schema = factory.newSchema(schemaLocation);       // создание валидатора на основе схемы
            Validator validator = schema.newValidator();       // проверка документа
            Source source = new StreamSource(fileName);
            validator.validate(source);
            result = true;
        } catch (
                SAXException e) {
            System.err.print("validation " + fileName + " is not valid because " + e.getMessage());
        } catch (
                IOException e) {
            System.err.print(fileName + " is not valid because " + e.getMessage());
        }
        return result;
    }
}