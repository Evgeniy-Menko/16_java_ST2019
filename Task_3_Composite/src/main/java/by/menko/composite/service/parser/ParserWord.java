package by.menko.composite.service.parser;

import by.menko.composite.bean.Component;
import by.menko.composite.bean.Composite;
import by.menko.composite.bean.CompositeType;
import by.menko.composite.bean.Symbol;

public class ParserWord implements DoChain {
    /**
     * next parser.
     */
    private DoChain chain;

    /**
     * set for next parser.
     *
     * @param nextChain object parser.
     */
    @Override
    public void setNextChain(final DoChain nextChain) {
        this.chain = nextChain;
    }

    /**
     * parse text on symbol.
     *
     * @param text for parser.
     *
     * @return component symbol.
     */
    @Override
    public Component dispense(final String text) {
        char[] arraySymbol = text.toCharArray();
        Component component = new Composite(CompositeType.WORD);
        for (char c : arraySymbol) {
            Component symbol = new Symbol(c);
            component.add(symbol);
        }
        return component;
    }
}
