package by.epam.thread.helloworld.ex15;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CalcRunner {
    public static void main(final String[] args) {
        int threadCount = 5;
        ExecutorService es = Executors.newFixedThreadPool(threadCount);
        Double d = 0d;
        for (int i = 0; i < threadCount; i++) {
            Future<Double> future = es.submit(new CalcCallable());
            try {
                d += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("%.5f", d);
        es.shutdown();
    }
}