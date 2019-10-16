package by.menko.composite.bean;

public class Symbol implements Component {
    private char i;
    CompositeType type = CompositeType.SYMBOL;

    public Symbol(final char symbol) {
        this.i = symbol;
    }


    @Override
    public String operation() {
        return String.valueOf(i);
    }

    @Override
    public CompositeType getType() {
        return type;
    }
}