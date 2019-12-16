package by.menko.finalproject.controller.action.useraction;


import by.menko.finalproject.controller.constantspath.ConstantsPath;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCommentCommand extends UserAction {
    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws PersonalException, ServletException, IOException {
        CommentService service = factory.createService(TypeServiceAndDao.COMMENT);

        UserInfo user = (UserInfo) request.getSession().getAttribute("authorizedUser");
        String idDisk = request.getParameter("idDisk");
        String commentText = request.getParameter("comment");
        service.addComment(idDisk,commentText, user.getIdEntity());
        response.sendRedirect(request.getContextPath()+ ConstantsPath.SHOW_DISK_WITH_PARAMETER + idDisk + "#com");
    }
}
