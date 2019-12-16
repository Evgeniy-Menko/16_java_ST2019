package by.menko.finalproject.entity;

import java.util.Date;

public class ShoppingCart extends Entity {

     private Integer diskId;
     private Date timeAdded;

    public Integer getDiskId() {
        return diskId;
    }

    public void setDiskId(Integer diskId) {
        this.diskId = diskId;
    }

    public Date getTimeAdded() {
            return timeAdded;
      }

      public void setTimeAdded(Date timeAdded) {
            this.timeAdded = timeAdded;
      }

    @Override
    public boolean equals(Object o) {
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
        result = 31 * result + diskId.hashCode();
        result = 31 * result + timeAdded.hashCode();
        return result;
    }
}
