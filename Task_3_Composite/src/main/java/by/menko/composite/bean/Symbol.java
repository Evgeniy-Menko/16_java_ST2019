package by.menko.composite.bean;


public class Symbol implements Component {
    /**
     * Symbol.
     */
    private char symbol;
    /**
     * Type.
     */
    private CompositeType type = CompositeType.SYMBOL;

    /**
     * Constructor.
     *
     * @param i char symbol.
     */
    public Symbol(final char i) {
        this.symbol = i;
    }

    /**
     * Collect text.
     *
     * @return text.
     */
    @Override
    public String collect() {
        return String.valueOf(symbol);
    }

    /**
     * get Type.
     *
     * @return type.
     */
    @Override
    public CompositeType getType() {
        return type;
    }

}
