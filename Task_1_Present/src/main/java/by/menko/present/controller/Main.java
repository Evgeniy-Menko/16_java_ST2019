package by.menko.present.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
      /*  Controller c = new Controller();
        c.startApp();*/
        int x = 33;
        int s = 0;
        while (x > 0) {
            s += x % 2;
            x /= 2;
            System.out.println("sd"+ (s+x) +";"+"x="+ x+" ;" + "s=" +s);
        }
        System.out.println(s);
    }

}

