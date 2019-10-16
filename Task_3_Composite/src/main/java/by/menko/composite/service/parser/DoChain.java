package by.menko.composite.service.parser;

import by.menko.composite.bean.Component;

public interface DoChain {
    void setNextChain(DoChain nextChain);

    Component dispense(String text);
}
