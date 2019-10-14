package by.menko.composite.service.parser;

import by.menko.composite.bean.Component;
import by.menko.composite.bean.Composite;
import by.menko.composite.bean.CompositeType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserText implements DispenseChain {

    private DispenseChain chain;


    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }


    @Override
    public void dispense(ResourcesForParser resources) {

        Pattern patternParagraph = Pattern
                .compile("[^\\t\\n]+|(?m)(?=^\\s{2})");
        Matcher matcher = patternParagraph.matcher(resources.getText());
        while (matcher.find()) {
            Component component = new Composite(CompositeType.PARAGRAPH);
            chain.dispense(new ResourcesForParser(matcher.group(), component));
            resources.getComp().add(component);
        }
    }
}
