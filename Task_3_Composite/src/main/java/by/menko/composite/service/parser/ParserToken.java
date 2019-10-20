package by.menko.composite.service.parser;

import by.menko.composite.bean.Component;
import by.menko.composite.bean.Composite;
import by.menko.composite.bean.CompositeType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserToken implements DoChain {
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
     * parse text on word.
     *
     * @param text for parser.
     *
     * @return component token.
     */
    @Override
    public Component dispense(final String text) {
        Component component = new Composite(CompositeType.TOKEN);
        Pattern patternWord = Pattern
                .compile("[\"]+|[']+|[.]+|[,]+|[!]+|[?]+|[\\w-&()><^~|+*\\/]+");
        Matcher matcher = patternWord.matcher(text);
        while (matcher.find()) {
            component.add(chain.dispense(matcher.group()));
        }
        return component;
    }
}

