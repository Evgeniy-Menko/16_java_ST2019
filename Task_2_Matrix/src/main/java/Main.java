import by.menko.matrix.bean.LockThreads;
import by.menko.matrix.bean.MyThread;
import by.menko.matrix.dal.storage.Matrix;
import by.menko.matrix.service.file.ServiceFile;
import by.menko.matrix.service.parser.ParserString;
import by.menko.matrix.service.validate.Validator;


import java.io.IOException;
import java.util.List;
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







     /*  for (int i = 1; i < 6; i++) {
            Thread t = new Thread(new MyThread(m));
            t.setName("Thread " + i);
            t.start();
            if (i == 5) {
                t.join();
            }
        }
       TimeUnit.SECONDS.sleep(1);*/

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }

    }
}
