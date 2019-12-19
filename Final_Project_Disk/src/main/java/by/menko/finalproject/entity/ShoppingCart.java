package by.menko.finalproject.entity;

import java.util.Date;

public class ShoppingCart extends Entity {
    /**
     * disk id.
     */
    private Integer diskId;
    /**
     * time added to shopping cart.
     */
    private Date timeAdded;

    public Integer getDiskId() {
        return diskId;
    }

    public void setDiskId(final Integer diskId) {
        this.diskId = diskId;
    }

    public Date getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(final Date timeAdded) {
        this.timeAdded = timeAdded;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ShoppingCart that = (ShoppingCart) o;

        if (!diskId.equals(that.diskId)) return false;
        return timeAdded.equals(that.timeAdded);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (diskId != null ? diskId.hashCode() : 0);
        result = 31 * result + (timeAdded != null ? timeAdded.hashCode() : 0);
        return result;
    }
}
