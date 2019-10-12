package by.epam.thread.helloworld.ex17;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Storage implements Iterable<Item> {
    public static final int DEFAULT_STORAGE_CAPACITY = 20;
    private Queue<Item> goods;

    private Storage() {
        goods = new LinkedBlockingQueue<>(DEFAULT_STORAGE_CAPACITY);
    }

    private Storage(int capacity) {
        goods = new LinkedBlockingQueue<>(capacity);
    }

    public static Storage createStorage(int capacity) {
        return new Storage(capacity);
    }

    public static Storage createStorage(int capacity, List<Item> goods) {
        Storage storage = new Storage(capacity);
        storage.goods.addAll(goods);
        return storage;
    }

    public Item getGood() {
        return goods.poll();
    }

    public boolean setGood(Item good) {
        return goods.add(good);
    }

    @Override
    public Iterator<Item> iterator() {
        return goods.iterator();
    }
}