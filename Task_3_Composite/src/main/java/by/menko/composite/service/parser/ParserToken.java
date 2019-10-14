package by.menko.composite.service.parser;

import by.menko.composite.bean.Component;
import by.menko.composite.bean.Composite;
import by.menko.composite.bean.CompositeType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserToken implements DispenseChain {
    private DispenseChain chain;


    @Override
    public void setNextChain(final DispenseChain nextChain) {
        this.chain = nextChain;
    }


    @Override
    public void dispense(final ResourcesForParser resources) {

        Pattern patternWord = Pattern
                .compile("[\\S]*[^\\W]|[^\\s]*[\\W]");
        Matcher matcher = patternWord.matcher(resources.getText());
        while (matcher.find()) {
            Component component = new Composite(CompositeType.TOKEN);
            chain.dispense(new ResourcesForParser(matcher.group(), component));
            resources.getComp().add(component);
        }
    }
}

