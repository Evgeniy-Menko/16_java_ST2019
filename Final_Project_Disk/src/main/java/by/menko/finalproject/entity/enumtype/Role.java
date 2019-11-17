package by.menko.finalproject.entity.enumtype;

public enum Role {

    ADMINISTRATOR("administrator"),
    USER("user");

    private String name;

    private Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
