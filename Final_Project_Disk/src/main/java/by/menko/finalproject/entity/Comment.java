package by.menko.finalproject.entity;

import java.util.Date;


public class Comment extends Entity {
    private Integer idUserCommented;
    private String commentText;
    private Integer idDisk;
    private Date timeAdded;

    public Integer getIdDisk() {
        return idDisk;
    }

    public void setIdDisk(Integer idDisk) {
        this.idDisk = idDisk;

    }

    public Integer getIdUserCommented() {
        return idUserCommented;
    }

    public Comment setIdUserCommented(Integer idUserCommented) {
        this.idUserCommented = idUserCommented;
        return this;
    }

    public String getCommentText() {
        return commentText;
    }

    public Comment setCommentText(String commentText) {
        this.commentText = commentText;
        return this;
    }

    public Date getTimeAdded() {
        return timeAdded;
    }

    public Comment setTimeAdded(Date timeAdded) {
        this.timeAdded = timeAdded;
        return this;
    }
}
