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

      public ShoppingCart setTimeAdded(Date timeAdded) {
            this.timeAdded = timeAdded;
            return this;
      }
}
