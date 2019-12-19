package by.menko.finalproject.entity;

import java.sql.Timestamp;
import java.util.Objects;


public class Complaint extends Entity {
    /**
     * id user complained.
     */
    private Integer userIdComplained;
    /**
     * Id user was complained.
     */
    private Integer userWasComplained;
    /**
     * id disk.
     */
    private Integer idDisk;
    /**
     * text complaint.
     */
    private String textComplaint;
    /**
     * time added complaint.
     */
    private Timestamp timeAdded;

    public Integer getUserIdComplained() {
        return userIdComplained;
    }

    public void setUserIdComplained(final Integer userIdComplained) {
        this.userIdComplained = userIdComplained;
    }

    public Integer getIdDisk() {
        return idDisk;
    }

    public void setIdDisk(final Integer idDisk) {
        this.idDisk = idDisk;
    }

    public Integer getUserWasComplained() {
        return userWasComplained;
    }

    public void setUserWasComplained(final Integer userWasComplained) {
        this.userWasComplained = userWasComplained;
    }

    public String getTextComplaint() {
        return textComplaint;
    }

    public void setTextComplaint(final String textComplaint) {
        this.textComplaint = textComplaint;
    }

    public Timestamp getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(final Timestamp timeAdded) {
        this.timeAdded = timeAdded;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Complaint complaint = (Complaint) o;

        if (!Objects.equals(userIdComplained, complaint.userIdComplained))
            return false;
        if (!Objects.equals(userWasComplained, complaint.userWasComplained))
            return false;
        if (!Objects.equals(idDisk, complaint.idDisk)) return false;
        if (!Objects.equals(textComplaint, complaint.textComplaint))
            return false;
        return Objects.equals(timeAdded, complaint.timeAdded);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (userIdComplained != null ? userIdComplained.hashCode() : 0);
        result = 31 * result + (userWasComplained != null ? userWasComplained.hashCode() : 0);
        result = 31 * result + (idDisk != null ? idDisk.hashCode() : 0);
        result = 31 * result + (textComplaint != null ? textComplaint.hashCode() : 0);
        result = 31 * result + (timeAdded != null ? timeAdded.hashCode() : 0);
        return result;
    }
}
