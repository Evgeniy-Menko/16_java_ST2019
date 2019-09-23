package by.menko.present.controller;

/**
 * @author Evgeniy Menko
 */
public final class Main {
    private Main() {
    }

    /**
     * Main thread.
     *
     * @param args .
     */
    public static void main(final String[] args) {
        Controller c = new Controller();
        c.startApp();

    }

}
