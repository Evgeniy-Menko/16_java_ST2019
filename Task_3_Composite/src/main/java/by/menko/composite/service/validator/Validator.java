package by.menko.composite.service.validator;


import by.menko.composite.view.MessageManager;

public class Validator {

    public boolean validateCharI(final String element) {
        char[] array = element.toCharArray();
        if (array.length == 1) {
            return true;
        } else {
            return false;
        }
    }
}
