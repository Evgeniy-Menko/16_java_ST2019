package by.epam.thread.helloworld.ex13;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tutor extends Thread {
    private Integer idTutor;
    private List<Student> list;

    public Tutor() {
        this.list = new ArrayList<>();
    }

    public Tutor(List<Student> list) {
        this.list = list;
    }

    public Integer getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Integer id) {
        this.idTutor = id;
    }

    public void run() {
        for (Student st : list) {
            // проверить, выданы ли студенту задания
            List<Task> tasks = st.getTaskList();
            for (Task current : tasks) {
                // проверить наличие ответа!
                int min = 8;
                int max = 10;

                int mark = new Random().nextInt((max - min) + 1) + min;
                current.setMark(mark);
                System.out.println(mark + " for student N " + st.getIdStudent());
                st.getCountDownLatch().countDown();
            }
            System.out.println("All estimates made for " + st.getIdStudent());
        }
    }
}