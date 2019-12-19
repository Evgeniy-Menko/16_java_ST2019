package by.menko.finalproject.entity;

import java.util.Date;
import java.util.Objects;


public class Comment extends Entity {
    /**
     * id user commented.
     */
    private Integer idUserCommented;
    /**
     * Comment text.
     */
    private String commentText;
    /**
     * id disk.
     */
    private Integer idDisk;
    /**
     * Time added.
     */
    private Date timeAdded;

    /**
     * Geter for id disk.
     * @return id disk.
     */
    public Integer getIdDisk() {
        return idDisk;
    }

    /**
     * seter for id disk.
     * @param idDisk .
     */
    public void setIdDisk(final Integer idDisk) {
        this.idDisk = idDisk;

    }

    /**
     * getter for id user was commented.
     * @return integer.
     */
    public Integer getIdUserCommented() {
        return idUserCommented;
    }

    /**
     * setter for id user.
     * @param idUserCommented .
     */
    public void setIdUserCommented(final Integer idUserCommented) {
        this.idUserCommented = idUserCommented;

    }

    /**
     * getter for comment text.
     * @return .
     */
    public String getCommentText() {
        return commentText;
    }

    /**
     * setter for comment text.
     * @param commentText .
     */
    public void setCommentText(final String commentText) {
        this.commentText = commentText;

    }

    /**
     * getter for time added.
     * @return .
     */
    public Date getTimeAdded() {
        return timeAdded;
    }

    /**
     * setter for time added.
     * @param timeAdded .
     */
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
