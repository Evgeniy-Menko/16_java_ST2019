package by.menko.finalproject.entity;

abstract public class Entity {
    private Integer idEntity;

    public Integer getIdEntity() {
        return idEntity;
    }

    public Entity setIdEntity(final Integer idEntity) {
        this.idEntity = idEntity;
        return this;
    }

    @Override
    public boolean equals(final Object object) {
        if (object != null) {
            if (object != this) {
                if (object.getClass() == getClass() && idEntity != null) {
                    return idEntity.equals(((Entity) object).idEntity);
                }
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return idEntity != null ? idEntity.hashCode() : 0;
    }
}
