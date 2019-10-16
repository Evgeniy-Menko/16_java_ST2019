package by.menko.composite.service.parser;

import by.menko.composite.bean.Component;
import by.menko.composite.bean.Composite;
import by.menko.composite.bean.CompositeType;
import by.menko.composite.bean.Symbol;

public class ParserWord implements DoChain {
    private DoChain chain;


    @Override
    public void setNextChain(final DoChain nextChain) {
        this.chain = nextChain;
    }


    @Override
    public Component dispense(final String text) {
        char[] arraySymbol = text.toCharArray();
        Component component = new Composite(CompositeType.WORD);
        for (int i = 0; i < arraySymbol.length; i++) {
            Component symbol = new Symbol(arraySymbol[i]);
            component.add(symbol);
        }
        return component;
    }
}
