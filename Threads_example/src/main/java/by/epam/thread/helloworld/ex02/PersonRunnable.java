package by.epam.thread.helloworld.ex02;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PersonRunnable extends Person implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(
            PersonRunnable.class);

    PersonRunnable(final String name) {
        super(name);
    }


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            String message = getName() + ": Hello world";
            LOGGER.debug(message);
        }
    }

    public static void main(String[] args) {
        Thread alice = new Thread(new PersonRunnable("Alice"));
        alice.setPriority(1);
        alice.start();

        Thread bob = new Thread(new PersonRunnable("Bob"));
        bob.setPriority(10);
        bob.start();
    }
}
