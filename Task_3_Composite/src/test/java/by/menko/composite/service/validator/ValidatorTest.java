package by.menko.composite.service.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ValidatorTest {
    private Validator validator = new Validator();

    @DataProvider(name = "dataForPositiveValidate")
    public Object[][] createDataForPositiveValidateDiagonal() {
        return new Object[][]{
                {new String[]{"a"}, true},
                {new String[]{","}, true},
                {new String[]{"1"}, true}
        };
    }

    @DataProvider(name = "dataForNegativeValidate")
    public Object[][] createDataForNegativeValidateDiagonal() {
        return new Object[][]{
                {new String[]{"1sd"}, false},
                {new String[]{"asd,"}, false},
                {new String[]{"we"}, false}
        };
    }

    @Test(description = " validate_values_a_positive", dataProvider = "dataForPositiveValidate")
    public void positiveValidate(String[] values, Boolean b) {
        Boolean actual = validator.validateCharI(values[0]);
        assertEquals(actual, b);

    }

    @Test(description = " validate_values_a_negative", dataProvider = "dataForNegativeValidate")
    public void negativeValidate(String[] values, Boolean b) {
        Boolean actual = validator.validateCharI(values[0]);
        assertEquals(actual, b);


    }
}
