package by.epam.thread.helloworld.ex01;

import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        IntStream.range(0, 5).forEach(i -> new MyThread("MyThread #" + i).start());
    }
}
