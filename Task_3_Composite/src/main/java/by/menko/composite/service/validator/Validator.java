package by.menko.composite.service.validator;


public class Validator {

    public boolean validateCharI(final String element) {
        boolean result = false;
        char[] array = element.toCharArray();
        if (array.length == 1) {
            result = true;
        }
        return result;
    }
}
