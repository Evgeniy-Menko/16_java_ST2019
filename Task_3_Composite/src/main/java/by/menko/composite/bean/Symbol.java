package by.menko.composite.bean;

public class Symbol implements Component {
    private char i;

    public Symbol(final char symbol) {
        this.i = symbol;
    }


    @Override
    public void operation() {
        String a = String.valueOf(i);
        System.out.println(a);
    }
}
