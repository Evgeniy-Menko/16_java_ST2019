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

    private final static String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    private final static String SCHEMA_NAME = "WEB-INF/classes/data/candies.xsd";
    private final SchemaFactory factory = SchemaFactory.newInstance(LANGUAGE);


    public boolean isValid(String fileName, String uri) {
        boolean result = false;
        try {
            File schemaLocation = new File(uri + SCHEMA_NAME);
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
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