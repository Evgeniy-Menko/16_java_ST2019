package by.menko.composite.service.parser;

import by.menko.composite.bean.Component;
import by.menko.composite.bean.Composite;
import by.menko.composite.bean.CompositeType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserText implements DoChain {

    private DoChain chain;


    @Override
    public void setNextChain(DoChain nextChain) {
        this.chain = nextChain;
    }


    @Override
    public Component dispense(String text) {
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
