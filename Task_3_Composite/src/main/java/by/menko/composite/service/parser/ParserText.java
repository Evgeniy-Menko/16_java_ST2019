package by.menko.composite.service.parser;

import by.menko.composite.bean.Component;
import by.menko.composite.bean.Composite;
import by.menko.composite.bean.CompositeType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserText implements DoChain {
    /**
     * next parser.
     */
    private DoChain chain;

    /**
     * set next parser.
     *
     * @param nextChain object parser.
     */
    @Override
    public void setNextChain(final DoChain nextChain) {
        this.chain = nextChain;
    }

    /**
     * parse text on paragraph.
     *
     * @param text for parser.
     *
     * @return component text.
     */
    @Override
    public Component dispense(final String text) {
        Component component = new Composite(CompositeType.TEXT);
        Pattern patternParagraph = Pattern
                .compile("[A-Z].*?(?=\\s{4}|\\n|^|$)");
        Matcher matcher = patternParagraph.matcher(text);
        while (matcher.find()) {
            component.add(chain.dispense(matcher.group()));
        }
        return component;
    }
}
