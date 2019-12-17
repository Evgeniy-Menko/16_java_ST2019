package by.menko.finalproject.entity;

import java.util.Date;
import java.util.Objects;


public class Comment extends Entity {
    private Integer idUserCommented;
    private String commentText;
    private Integer idDisk;
    private Date timeAdded;

    public Integer getIdDisk() {
        return idDisk;
    }

    public void setIdDisk(final Integer idDisk) {
        this.idDisk = idDisk;

    }

    public Integer getIdUserCommented() {
        return idUserCommented;
    }

    public void setIdUserCommented(final Integer idUserCommented) {
        this.idUserCommented = idUserCommented;

    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(final String commentText) {
        this.commentText = commentText;

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

        Comment comment = (Comment) o;

        if (!Objects.equals(idUserCommented, comment.idUserCommented))
            return false;
        if (!Objects.equals(commentText, comment.commentText)) return false;
        if (!Objects.equals(idDisk, comment.idDisk)) return false;
        return Objects.equals(timeAdded, comment.timeAdded);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (idUserCommented != null ? idUserCommented.hashCode() : 0);
        result = 31 * result + (commentText != null ? commentText.hashCode() : 0);
        result = 31 * result + (idDisk != null ? idDisk.hashCode() : 0);
        result = 31 * result + (timeAdded != null ? timeAdded.hashCode() : 0);
        return result;
    }
}
