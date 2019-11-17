package by.menko.finalproject.entity;

import java.util.Date;

public class Complaint  extends Entity{

   private Integer userIdComplained;
   private Integer userWasComplained;
   private String textComplaint;
   private Date timeAdded;

    public Integer getUserIdComplained() {
        return userIdComplained;
    }

    public Complaint setUserIdComplained(Integer userIdComplained) {
        this.userIdComplained = userIdComplained;
        return this;
    }


    public Integer getUserWasComplained() {
        return userWasComplained;
    }

    public Complaint setUserWasComplained(Integer userWasComplained) {
        this.userWasComplained = userWasComplained;
        return this;
    }

    public String getTextComplaint() {
        return textComplaint;
    }

    public Complaint setTextComplaint(String textComplaint) {
        this.textComplaint = textComplaint;
        return this;
    }

    public Date getTimeAdded() {
        return timeAdded;
    }

    public Complaint setTimeAdded(Date timeAdded) {
        this.timeAdded = timeAdded;
        return this;
    }
}
