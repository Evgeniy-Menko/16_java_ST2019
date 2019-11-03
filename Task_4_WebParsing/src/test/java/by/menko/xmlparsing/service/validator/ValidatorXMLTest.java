package by.menko.xmlparsing.service.validator;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class ValidatorXMLTest {
    private ValidatorXML validatorXML = new ValidatorXML();


    @DataProvider(name = "dataForNegativeReadFile")
    public Object[][] createDataForNegativeValidateFile() {
        return new Object[][]{
                {new String[]{"src//test//resources//data//candies.xsd", "src//test//resources//data//candies1.xml"}},
                {new String[]{"src//test//resources//data//candies.xsd", "src//test//resources//data//candies2.xml"}},
                {new String[]{"src//test//resources//data//candies.xsd", "src//test//resources//data//candies3.xml"}},
        };
    }


    @Test(description = " negative_read_file", dataProvider = "dataForNegativeReadFile")
    public void negativeReadFile(String[] values) {
        boolean actual = validatorXML.isValid(values[1], values[0]);
        assertEquals(actual, false);
    }

}
