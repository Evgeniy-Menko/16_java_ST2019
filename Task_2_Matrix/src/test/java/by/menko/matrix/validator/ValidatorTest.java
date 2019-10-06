package by.menko.matrix.validator;

import by.menko.matrix.service.validate.Validator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ValidatorTest {
    private Validator validator = new Validator();
    private List<String> list;

    @DataProvider(name = "dataForPositiveValidateFile")
    public Object[][] createDataForPositiveValidateFile() {
        List<String> a = new ArrayList<>();
        List<String> b = new ArrayList<>();
        List<String> c = new ArrayList<>();

        a.add(0, "5");
        a.add(1, "0 2 3");
        a.add(2, "1 0 3");
        a.add(3, "1 2 0");


        b.add(0, "4");
        b.add(1, "0 2 3 4 5");
        b.add(2, "1 0 3 4 5");
        b.add(3, "1 2 0 4 5");
        b.add(4, "1 2 3 0 5");
        b.add(5, "1 2 3 4 0");

        c.add(0, "6");
        c.add(1, "0 2 3 4");
        c.add(2, "1 0 3 4");
        c.add(3, "1 2 0 4");
        c.add(4, "1 2 3 0");
        return new Object[][]{
                {a, true},
                {b, true},
                {c, true}};
    }

    @DataProvider(name = "dataForNegativeValidateFile")
    public Object[][] createDataForNegativeValidateFile() {
        List<String> a = new ArrayList<>();
        List<String> b = new ArrayList<>();
        List<String> c = new ArrayList<>();
        List<String> d = new ArrayList<>();
        List<String> e = new ArrayList<>();

        a.add(0, "7");
        a.add(1, "0 2 3");
        a.add(2, "1 0 3");
        a.add(3, "1 2 0");

        b.add(0, "4");
        b.add(1, "0 2 3 4 5");
        b.add(2, "1 1 3 4 5");
        b.add(3, "1 2 0 4 5");
        b.add(4, "1 2 3 0 5");
        b.add(5, "1 2 3 4 0");

        c.add(0, "6");
        c.add(1, "0 2 s 4");
        c.add(2, "1 0 3 4");
        c.add(3, "1 2 0 4");
        c.add(4, "1 2 3 0");

        d.add(0, "6");
        d.add(1, "0 2 3 4");
        d.add(2, "-1 0 3 4");
        d.add(3, "1 2 0 4");
        d.add(4, "1 2 3 0");

        e.add(0, "6");
        e.add(1, "0 2 3 4");
        e.add(2, "1 0 3 4");
        e.add(3, "1 2 0 4");

        return new Object[][]{
                {a, false},
                {b, false},
                {c, false},
                {d, false},
                {e, false}};
    }

    @DataProvider(name = "dataForPositiveValidateDiagonal")
    public Object[][] createDataForPositiveValidateDiagonal() {
        return new Object[][]{
                {new String[]{"1", "2", "3", "4"}, true},
                {new String[]{"0", "1", "2", "3", "5"}, true},
                {new String[]{"1", "2"}, true}
        };
    }

    @DataProvider(name = "dataForNegativeValidateDiagonal")
    public Object[][] createDataForNegativeValidateDiagonal() {
        return new Object[][]{
                {new String[]{"1", "2", "3", "5"}, false},
                {new String[]{"0", "s", "2", "3", "5"}, false},
                {new String[]{"1", "2", "3", "2"}, false}
        };
    }

    @Test(description = " validate_values_a_positive", dataProvider = "dataForPositiveValidateFile")
    public void positiveValidateFileTest(List<String> values, Boolean b) {
        Boolean actual = validator.validateMatrix(values);
        assertEquals(actual, b);

    }

    @Test(description = " validate_values_a_negative", dataProvider = "dataForNegativeValidateFile")
    public void negativeValidateFileTest(List<String> values, Boolean b) {
        Boolean actual = validator.validateMatrix(values);
        assertEquals(actual, b);

    }

    @Test(description = " validate_values_a_positive", dataProvider = "dataForPositiveValidateDiagonal")
    public void positiveValidateDiagonal(String[] valuesDiagonal, Boolean b) {
        Boolean actual = validator.validateValuesDiagonal(valuesDiagonal, Integer.parseInt(valuesDiagonal[valuesDiagonal.length - 1]));
        assertEquals(actual, b);

    }

    @Test(description = " validate_values_a_negative", dataProvider = "dataForNegativeValidateDiagonal")
    public void negativeValidateDiagonal(String[] valuesDiagonal, Boolean b) {
        Boolean actual = validator.validateValuesDiagonal(valuesDiagonal, Integer.parseInt(valuesDiagonal[valuesDiagonal.length - 1]));
        assertEquals(actual, b);

    }

}
