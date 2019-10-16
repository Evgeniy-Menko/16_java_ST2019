package by.menko.composite.service.parser;

public class ManagerChain {
    private DoChain parser;

    public ManagerChain() {
        // initialize the chain
        this.parser = new ParserText();
        DoChain c2 = new ParserParagraph();
        DoChain c3 = new ParserSentence();
        DoChain c4 = new ParserToken();
        DoChain c5 = new ParserWord();

        // set the chain of responsibility
        parser.setNextChain(c2);
        c2.setNextChain(c3);
        c3.setNextChain(c4);
        c4.setNextChain(c5);
    }

    public DoChain getParser() {
        return parser;
    }
}
