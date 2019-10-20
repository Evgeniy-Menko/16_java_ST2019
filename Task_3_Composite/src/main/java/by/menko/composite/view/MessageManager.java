package by.menko.composite.view;

import java.util.Locale;
import java.util.ResourceBundle;

public enum MessageManager {
    /**
     * English language.
     */
    EN(ResourceBundle.getBundle("bundle.text", new Locale("en", "US"))),
    /**
     * Russian language.
     */
    RU(ResourceBundle.getBundle("bundle.text", new Locale("ru", "RU"))),
    /**
     * Deutsch language.
     */
    DE(ResourceBundle.getBundle("bundle.text", new Locale("de", "DE")));
    /**
     * resource bundle.
     */
    private ResourceBundle bn;

    /**
     * Constructor.
     *
     * @param bundle resource bundle.
     */
    MessageManager(final ResourceBundle bundle) {
        this.bn = bundle;
    }

    /**
     * get message.
     * @param key key for bundle.
     * @return text.
     */
    public String getMessage(final String key) {
        return bn.getString(key);
    }
}
