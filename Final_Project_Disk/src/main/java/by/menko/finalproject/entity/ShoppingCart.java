package by.menko.finalproject.entity;

import java.util.Date;

public class ShoppingCart extends Entity {
     private Integer diskID;
     private Date timeAdded;

      public Integer getDisk_id() {
            return diskID;
      }

      public ShoppingCart setDisk_id(Integer idDisk) {
            this.diskID = idDisk;
            return this;
      }

      public Date getTimeAdded() {
            return timeAdded;
      }

      public ShoppingCart setTimeAdded(Date timeAdded) {
            this.timeAdded = timeAdded;
            return this;
      }
}
