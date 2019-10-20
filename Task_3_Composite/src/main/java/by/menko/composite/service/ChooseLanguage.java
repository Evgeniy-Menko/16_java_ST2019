package by.menko.composite.service;

import by.menko.composite.controller.Controller;
import by.menko.composite.view.MessageManager;


public class ChooseLanguage {
    /**
     * Change Language.
     *
     * @return result.
     */
    public String changeLanguage() {
        String param = new Controller().getScan().nextLine();
        switch (param) {
            case "1":
                new Controller().setBundle(MessageManager.EN);
                return "changeLanguage";
            case "2":
                new Controller().setBundle(MessageManager.RU);
                return "changeLanguage";
            case "3":
                new Controller().setBundle(MessageManager.DE);
                return "changeLanguage";
            default:
                return "incorrect";
        }
    }
}
