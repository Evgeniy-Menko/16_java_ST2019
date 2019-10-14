package by.menko.composite.service.parser;

import by.menko.composite.bean.Component;
import by.menko.composite.bean.Composite;
import by.menko.composite.bean.CompositeType;
import by.menko.composite.bean.Symbol;

public class ParserWord implements DispenseChain {
    private DispenseChain chain;


    @Override
    public void setNextChain(final DispenseChain nextChain) {
        this.chain = nextChain;
    }


    @Override
    public void dispense(final ResourcesForParser resources) {
        char[] symbol = resources.getText().toCharArray();

        for (int i = 0; i < symbol.length; i++) {
            Component component = new Symbol(symbol[i]);
            resources.getComp().add(component);
        }
    }
}
