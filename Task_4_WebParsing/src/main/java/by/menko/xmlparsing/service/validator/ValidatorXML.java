package by.menko.xmlparsing.service.validator;

import by.menko.xmlparsing.service.validator.exception.ValidException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    /**
     * Object log4j2.
     */
    private Logger log = LogManager.getLogger();
    /**
     * Language.
     */
    private final static String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    /**
     * Schema url.
     */
    private final static String SCHEMA_NAME = "WEB-INF/classes"
            + "/data/candies.xsd";
    /**
     * Factory.
     */
    private final SchemaFactory factory = SchemaFactory.newInstance(LANGUAGE);

    /**
     * Validate xml file.
     *
     * @param fileName .
     * @param uri      .
     *
     * @return true or false.
     */
    public boolean isValid(final String fileName, final String uri) {
        boolean result = false;
        try {
            File schemaLocation = new File(uri + SCHEMA_NAME);
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.validate(source);
            result = true;
        } catch (SAXException | IOException e) {
            log.info("File successfully parsed: " + fileName + ".");
        }
        return result;
    }
}