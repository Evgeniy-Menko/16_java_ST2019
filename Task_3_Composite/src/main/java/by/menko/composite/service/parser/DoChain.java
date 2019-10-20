package by.menko.composite.service.parser;

import by.menko.composite.bean.Component;

public interface DoChain {
    /**
     * Set next parser.
     *
     * @param nextChain object parser.
     */
    void setNextChain(DoChain nextChain);

    /**
     * overried method for parser.
     *
     * @param text for parser.
     *
     * @return component.
     */
    Component dispense(String text);
}
