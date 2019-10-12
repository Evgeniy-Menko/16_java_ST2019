package by.epam.thread.helloworld.ex15;

import java.util.Random;
import java.util.concurrent.Callable;

public class CalcCallable implements Callable<Double> {
    @Override
    public Double call() throws Exception {
        Double res = new Random().nextGaussian(); // имитация вычислений
        return res;
    }
}
