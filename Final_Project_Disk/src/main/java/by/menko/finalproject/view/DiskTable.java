package by.menko.finalproject.view;

import by.menko.finalproject.entity.Disk;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DiskTable extends SimpleTagSupport {

    /**
     * The start tag of the table head.
     */
    private static final String TABLE_HEAD_TAG_START =
            "<div class=\"col-lg-8 \"><table class=\"table border \" "
                    + "style=\"margin-left: 4%\"><thead><tr>";

    /**
     * The start tag of the table head with croll.
     */

    private static final String TABLE_HEAD_TAG_START_WITH_SCROLL =
            "<div class=\"col-lg-8 \"><table class=\"table border \" "
                    + "style=\"margin-left: 4%;overflow-y: auto; height:1000px\"><thead><tr>";
    /**
     * The end tag of the table head.
     */
    private static final String TABLE_HEAD_TAG_END =
            "</tr><tr><th></th><th>Name</th><th>Price</th>"
                    + "<th>Time added</th><th></th></tr></thead><tbody>";
    /**
     * The end tag of the table.
     */
    private static final String TABLE_TABLE_TAG_END = "</tbody></table>";
    /**
     * Start tag for error message.
     */
    private static final String ERROR_START_TAG =
            "<th colspan=\"5\" style=\"color: #b30300;text-align: center\">"
                    + "<fmt:message key=\"";
    /**
     * End tag for error message.
     */
    private static final String ERROR_END_TAG = "\"/></th>";
    /**
     * Empty list message.
     */
    private static final String EMPTY_LIST = "<th colspan=\"5\" "
            + "style=\"text-align: "
            + "center\">Not found</th>";

    /**
     * The list of available disks.
     */
    private List<Disk> disks;

    /**
     * Error message.
     */
    private String errorMessage;

    /**
     * Path to page context.
     */
    private String path;

    /**
     * Sets disks.
     *
     * @param disksValue value of disks.
     */
    public void setDisks(final List<Disk> disksValue) {
        disks = disksValue;
    }

    /**
     * Sets errorMessage.
     *
     * @param errorMessageValue value of errorMessage.
     */
    public void setErrorMessage(final String errorMessageValue) {
        errorMessage = errorMessageValue;
    }

    /**
     * Sets path.
     *
     * @param pathValue value of path.
     */
    public void setPath(final String pathValue) {
        path = pathValue;
    }

    /**
     * Prints available disks in the table.
     *
     * @throws JspException      Subclasses can throw JspException to indicate
     *                           an error occurred while processing this tag.
     * @throws SkipPageException If the page that (either directly or
     *                           indirectly) invoked this tag is to
     *                           cease evaluation.
     */
    @Override
    public void doTag() throws JspException {
        StringBuilder sb = new StringBuilder();
        if (disks.size() > 10) {
            sb.append(TABLE_HEAD_TAG_START_WITH_SCROLL);
        } else {
            sb.append(TABLE_HEAD_TAG_START);
        }
        if (disks.isEmpty()) {
            sb.append(EMPTY_LIST);
        } else {
            sb.append(TABLE_HEAD_TAG_END);
            for (Disk disk : disks) {
                writeDiskValues(sb, disk);
            }
        }
        sb.append(TABLE_TABLE_TAG_END);
        try {
            getJspContext().getOut().write(sb.toString());
        } catch (Exception e) {
            String message = "Cannot execute tag body";
            throw new SkipPageException(message, e);
        }
    }

    /**
     * Fills the table by disk data.
     *
     * @param sb   the provided string builder.
     * @param disk the provided sik entity.
     */
    private void writeDiskValues(final StringBuilder sb, final Disk disk) {
        sb.append("<tr><td><img src=\"")
                .append(disk.getImage())
                .append("\" alt=\"no image\" height=\"100\" width=\"100\"></td><td>")
                .append(disk.getNameDisk())
                .append("</td><td>")
                .append(disk.getPrice())
                .append("</td><td>")
                .append(disk.getTimeAdded()
                        .toLocalDateTime()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")))
                .append("</td><td><a href=\"")
                .append(path)
                .append("/showDisk.html?disk=")
                .append(disk.getIdEntity())
                .append("\" class=\"nav-link\">More</a></td></tr>");
    }
}
