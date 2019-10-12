package by.epam.thread.helloworld.ex12;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Bid extends Thread {
    private Integer bidId;
    private int price;
    private CyclicBarrier barrier;

    private Random random = new Random();

    public Bid(final int idValue, final int priceValue,
               final CyclicBarrier barrierValue) {
        this.bidId = idValue;
        this.price = priceValue;
        this.barrier = barrierValue;
    }

    public Integer getBidId() {
        return bidId;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public void run() {

        try {
            System.out.println("Client " + this.bidId + " specifies a price.");
            Thread.sleep(random.nextInt(3000)); // время на раздумье
            // определение уровня повышения цены
            int delta = random.nextInt(50);
            price += delta;
            System.out.println("Bid " + this.bidId + " : " + price);
            this.barrier.await(); // остановка у барьера
            System.out.println("Continue to work..."); // проверить кто выиграл
            // и оплатить в случае победы ставки
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}