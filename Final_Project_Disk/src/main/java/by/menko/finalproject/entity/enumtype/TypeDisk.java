package by.menko.finalproject.entity.enumtype;

public enum TypeDisk {
    FILM("Film"),
    GAME("Game"),
    MUSIC("Music");

    private String name;

    private TypeDisk(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getIdTypeDisk() {
        return ordinal();
    }

    public static Role getByIdTypeDisk(Integer id) {
        return Role.values()[id];
    }
}
