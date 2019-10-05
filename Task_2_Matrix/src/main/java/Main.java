import by.menko.matrix.bean.ExecutorThreads;
import by.menko.matrix.bean.LockThreads;
import by.menko.matrix.bean.SemaphoreThreads;
import by.menko.matrix.dal.matrix.Matrix;
import by.menko.matrix.service.file.ServiceFile;
import by.menko.matrix.service.parser.ParserString;
import by.menko.matrix.service.validate.Validator;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServiceFile s = new ServiceFile();
        ParserString p = new ParserString();
        Validator v = new Validator();
        List<String> l = p.parseToList(s.fileReader("data//File.txt"));

        Matrix m1 = Matrix.getInstance();
        m1.addMatrix(l);
        int[][] m = m1.getMatrix();

     /* Semaphore sem = new Semaphore(5,true);
        for (int i = 1; i <= 10; i++) {
            Thread t = new Thread(new SemaphoreThreads(sem, "Threads " + i, m,i-1));
            t.start();
            if (i == 10) {
                t.join();
            }


        }
*/
        //TimeUnit.SECONDS.sleep(3);


     /*ReentrantLock locker = new ReentrantLock(); // создаем заглушку
        for (int i = 1; i < 6; i++) {
            Thread t = new Thread(new LockThreads(m, locker));
            t.setName("Thread " + i);
            t.start();
            if (i == 5) {
                t.join();
            }
        }*/
        // TimeUnit.SECONDS.sleep(2);
        System.out.println(m.length);
        ExecutorService executor;
        executor = Executors.newFixedThreadPool(5);
        for (int i = 1; i < 6; i++) {
            executor.execute(new ExecutorThreads("Thread " + i, m));
        }

        TimeUnit.SECONDS.sleep(3);
        executor.shutdown();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }

    }
}
