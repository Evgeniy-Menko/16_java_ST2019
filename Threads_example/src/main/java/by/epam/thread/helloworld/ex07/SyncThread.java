package by.epam.thread.helloworld.ex07;

public class SyncThread extends Thread{
    private Resource resource;

    SyncThread(final String name, final Resource resourceValue) {
        super(name);
        resource = resourceValue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            resource.writing(getName(), i);
        }
    }
}
