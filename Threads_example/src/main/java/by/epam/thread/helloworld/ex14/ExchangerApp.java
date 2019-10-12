package by.epam.thread.helloworld.ex14;

import java.util.concurrent.Exchanger;

public class ExchangerApp {
    private static int n = 16;

    public static void main(String[] args) {

        Exchanger<String> ex = new Exchanger();

        for (int i = 1; i <= n; i++) {

            Thread t1 = new Thread(new PutThread(ex));
            Thread t2 = new Thread(new GetThread(ex));

            t1.setName("Поток Put " + i);
            t2.setName("Поток Get " + i);

            t1.start();
            t2.start();
        }
    }
}

class PutThread implements Runnable {

    Exchanger<String> exchanger;
    String message;

    PutThread(Exchanger ex) {

        this.exchanger = ex;
        message = "Hello Java!";
    }

    public void run() {

        try {
            message += " - It is the message from PutThread (" + Thread.currentThread()
                                                                       .getName() + ")";
            message = exchanger.exchange(message);
            System.out.println("PutThread " + Thread.currentThread()
                                                    .getName() + " получил: "
                    + message);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

class GetThread implements Runnable {

    Exchanger<String> exchanger;
    String message;

    GetThread(Exchanger ex) {

        this.exchanger = ex;
        message = "Привет мир!";
    }

    public void run() {

        try {
            message += " - It is the message from GetThread (" + Thread.currentThread()
                                                                       .getName() + ")";
            message = exchanger.exchange(message);
            System.out.println("GetThread " + Thread.currentThread()
                                                    .getName() + " получил: "
                    + message);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

