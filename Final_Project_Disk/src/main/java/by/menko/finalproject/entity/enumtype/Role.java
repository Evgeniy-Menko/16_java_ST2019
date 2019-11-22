package by.menko.finalproject.entity.enumtype;

public enum Role {

    USER("User"),
    ADMINISTRATOR("Administrator");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getIdRole() {
        return ordinal();
    }

    public static Role getByIdRole(Integer id) {
        return Role.values()[id];
    }
}
