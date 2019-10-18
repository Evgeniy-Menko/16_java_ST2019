package by.menko.composite.view;

import java.util.Locale;
import java.util.ResourceBundle;

public enum MessageManager {

    EN(ResourceBundle.getBundle("bundle.text", new Locale("en", "US"))),
    RU(ResourceBundle.getBundle("bundle.text", new Locale("ru", "RU"))),
    DE(ResourceBundle.getBundle("bundle.text", new Locale("de", "DE")));

    private ResourceBundle bn;

    MessageManager(final ResourceBundle bundle) {
        this.bn = bundle;
    }

    public String getMessage(final String key) {
        return bn.getString(key);
    }
}
