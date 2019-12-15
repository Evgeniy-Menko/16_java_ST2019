package by.menko.finalproject.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Complaint extends Entity {

    private Integer userIdComplained;
    private Integer userWasComplained;
    private Integer idDisk;
    private String textComplaint;
    private Timestamp timeAdded;

    public Integer getUserIdComplained() {
        return userIdComplained;
    }

    public Complaint setUserIdComplained(Integer userIdComplained) {
        this.userIdComplained = userIdComplained;
        return this;
    }

    public Integer getIdDisk() {
        return idDisk;
    }

    public void setIdDisk(Integer idDisk) {
        this.idDisk = idDisk;
    }

    public Integer getUserWasComplained() {
        return userWasComplained;
    }

    public void setUserWasComplained(Integer userWasComplained) {
        this.userWasComplained = userWasComplained;
    }

    public String getTextComplaint() {
        return textComplaint;
    }

    public void setTextComplaint(String textComplaint) {
        this.textComplaint = textComplaint;
    }

    public Timestamp getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Timestamp timeAdded) {
        this.timeAdded = timeAdded;

    }
}
