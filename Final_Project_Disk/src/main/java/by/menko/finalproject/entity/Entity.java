package by.menko.finalproject.entity;

public abstract class Entity {
    /**
     * id entity.
     */
    private Integer idEntity;

    public Integer getIdEntity() {
        return idEntity;
    }

    public Entity setIdEntity(final Integer idEntity) {
        this.idEntity = idEntity;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        return idEntity != null ? idEntity.equals(entity.idEntity) : entity.idEntity == null;
    }

    @Override
    public int hashCode() {
        return idEntity != null ? idEntity.hashCode() : 0;
    }
}
