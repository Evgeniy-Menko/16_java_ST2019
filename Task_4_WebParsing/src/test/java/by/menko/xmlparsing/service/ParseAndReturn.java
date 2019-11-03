package by.menko.xmlparsing.service;

import by.menko.xmlparsing.bean.Candy;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class ParseAndReturn {
    private GetListCandy getListCandy = new GetListCandy();


    @DataProvider(name = "dataForPositiveReadFile")
    public Object[][] createDataForPositiveValidateFile() {
        return new Object[][]{
                {new String[]{"DOM", "src//test//resources//candies.xml"}, 16},
                {new String[]{"SAX", "src//test//resources//candies.xml"}, 16},
                {new String[]{"STAX", "src//test//resources//candies.xml"}, 16},
        };
    }

    @DataProvider(name = "dataForNegativeReadFile")
    public Object[][] createDataForNegativeValidateFile() {
        return new Object[][]{
                {new String[]{"DOM", "src//test//resources//data//candies1.xml"}},
                {new String[]{"SAX", "src//test//resources//data//candies2.xml"}},
                {new String[]{"STAX", "src//test//resources//data//candies3.xml"}},
        };
    }

    @Test(description = " positive_read_file", dataProvider = "dataForPositiveReadFile")
    public void positiveReadFile(String[] values, int result) throws IOException, ParserConfigurationException, XMLStreamException, SAXException {

        List<Candy> actual = getListCandy.getListCandy(values[0], values[1]);
        assertEquals(actual.size(), result);
    }

    @Test(description = " negative_read_file", dataProvider = "dataForNegativeReadFile")
    public void negativeReadFile(String[] values) {
        assertThrows(Exception.class, () -> getListCandy.getListCandy(values[0], values[1]));
    }

}

