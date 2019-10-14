package by.menko.composite.service.parser;

public interface DispenseChain {
    void setNextChain(DispenseChain nextChain);

    void dispense(ResourcesForParser resources);
}
