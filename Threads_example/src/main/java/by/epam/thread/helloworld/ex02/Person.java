package by.epam.thread.helloworld.ex02;

public class Person {

    private String name;

    Person(final String name) {
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return value of name.
     */
    String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name value of name.
     */
    public void setName(final String name) {
        this.name = name;
    }
}
