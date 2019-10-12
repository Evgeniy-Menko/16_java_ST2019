package by.epam.thread.helloworld.ex01;

public class MyThread extends Thread {

    MyThread(final String s) {
        this.setName(s);
    }

    @Override
    public void run() {
        System.out.println(this.getName());
    }
}
