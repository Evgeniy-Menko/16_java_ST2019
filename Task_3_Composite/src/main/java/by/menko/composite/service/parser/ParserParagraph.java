package by.menko.composite.service.parser;

import by.menko.composite.bean.Component;
import by.menko.composite.bean.Composite;
import by.menko.composite.bean.CompositeType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserParagraph implements DoChain {
    /**
     * next parser.
     */
    private DoChain chain;

    /**
     * Set next parser.
     *
     * @param nextChain object parser.
     */
    @Override
    public void setNextChain(final DoChain nextChain) {
        this.chain = nextChain;
    }

    /**
     * parse text on sentence.
     *
     * @param text for parser.
     *
     * @return component paragraph.
     */
    @Override
    public Component dispense(final String text) {
        Component component = new Composite(CompositeType.PARAGRAPH);
        Pattern patternSentence = Pattern
                .compile("[^\\s][^.|!|?]*[.|!|?]{0,3}");
        Matcher matcher = patternSentence.matcher(text);
        while (matcher.find()) {

            component.add(chain.dispense(matcher.group()));

        }
        return component;
    }
}
