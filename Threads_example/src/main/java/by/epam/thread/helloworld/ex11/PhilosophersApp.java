package by.epam.thread.helloworld.ex11;

import java.util.concurrent.Semaphore;

public final class PhilosophersApp {

    public static void main(final String[] args) {

        Semaphore sem = new Semaphore(5);
        for (int i = 1; i <= 13; i++) {
            new Philosopher(sem, i).start();
        }
    }
}

class Philosopher extends Thread {
    private Semaphore sem; // семафор. ограничивающий число философов
    // кол-во приемов пищи
    private int num = 0;
    // условный номер философа
    private int id;

    // в качестве параметров конструктора передаем идентификатор философа и
    // семафор
    Philosopher(final Semaphore sem, final int id) {
        this.sem = sem;
        this.id = id;
    }

    public void run() {
        try {
            while (num < 3)// пока количество приемов пищи не достигнет 3
            {
                // Запрашиваем у семафора разрешение на выполнение
                sem.acquire();
                System.out.println("Философ " + id + " садится за стол");
                // философ ест
                sleep(500);
                num++;

                System.out.println("Философ " + id + " выходит из-за стола");
                sem.release();

                // философ гуляет
                sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("у философа " + id + " проблемы со здоровьем");
        }
    }
}
