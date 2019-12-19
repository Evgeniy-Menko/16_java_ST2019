package by.menko.finalproject.controller.action.useraction;


import by.menko.finalproject.controller.constantspath.ConstantsPath;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.CommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCommentCommand extends UserAction {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws PersonalException, ServletException, IOException {
        CommentService service = factory.createService(TypeServiceAndDao.COMMENT);
        UserInfo user = (UserInfo) request.getSession().getAttribute("authorizedUser");
        String idDisk = request.getParameter("disk");
        String idComment = request.getParameter("com");
        service.deleteComment(idComment, user.getIdEntity());
        String message = String.format("User %d deleted comment %s on announcement %s", user.getIdEntity(), idComment, idDisk);
        logger.info(message);
        response.sendRedirect(request.getContextPath() + ConstantsPath.SHOW_DISK_WITH_PARAMETER + idDisk + "#com");
    }
}
