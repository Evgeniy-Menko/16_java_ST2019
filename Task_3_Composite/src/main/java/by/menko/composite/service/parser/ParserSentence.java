package by.menko.composite.service.parser;

import by.menko.composite.bean.Component;
import by.menko.composite.bean.Composite;
import by.menko.composite.bean.CompositeType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserSentence implements DoChain {
    private DoChain chain;


    @Override
    public void setNextChain(final DoChain nextChain) {
        this.chain = nextChain;
    }


    @Override
    public Component dispense(final String text) {
        Component component = new Composite(CompositeType.SENTENCE);
        Pattern patternToken = Pattern
                .compile("[(\\S)]*[^\\s]");
        Matcher matcher = patternToken.matcher(text);
        while (matcher.find()) {
            component.add(chain.dispense(matcher.group()));
        }
        return component;
    }
}
