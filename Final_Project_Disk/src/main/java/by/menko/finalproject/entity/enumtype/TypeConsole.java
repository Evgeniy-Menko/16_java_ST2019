package by.menko.finalproject.entity.enumtype;

public enum TypeConsole {
    PS4("PS4"),
    XBOX("XBOX"),
    MUSIC("PC");

    private String name;

     TypeConsole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getIdTypeConsole() {
        return ordinal();
    }

    public static Role getByIdTypeConsole(Integer id) {
        return Role.values()[id];
    }
}
