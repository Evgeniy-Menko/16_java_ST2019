package by.menko.composite.service.parser;

import by.menko.composite.bean.Component;

public class ResourcesForParser {
    private String text;
    private Component comp;

    public ResourcesForParser(final String line, final Component component) {
        this.text = line;
        this.comp = component;
    }

    public String getText() {
        return text;
    }

    public Component getComp() {
        return comp;
    }
}
