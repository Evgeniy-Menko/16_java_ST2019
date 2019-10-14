package by.menko.composite.service.parser;

public class ExecutionChain {
    private DispenseChain c1;

    public ExecutionChain() {
        // initialize the chain
        this.c1 = new ParserText();
        DispenseChain c2 = new ParserParagraph();
        DispenseChain c3 = new ParserSentence();
        DispenseChain c4 = new ParserToken();
        DispenseChain c5 = new ParserWord();

        // set the chain of responsibility
        c1.setNextChain(c2);
        c2.setNextChain(c3);
        c3.setNextChain(c4);
        c4.setNextChain(c5);
    }

    public DispenseChain getC1() {
        return c1;
    }
}
