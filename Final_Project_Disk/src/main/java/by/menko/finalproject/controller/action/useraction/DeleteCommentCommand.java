package by.menko.finalproject.controller.action.useraction;

import by.menko.finalproject.controller.action.Command;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCommentCommand extends Command {
    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws PersonalException, ServletException, IOException {
        CommentService service = factory.createService(TypeServiceAndDao.COMMENT);
        UserInfo user = (UserInfo) request.getSession().getAttribute("authorizedUser");
        String idDisk = request.getParameter("disk");
        String idComment = request.getParameter("com");
        service.deleteComment(idComment, user.getIdEntity());
        response.sendRedirect("/Panda-Disk/showDisk.html?disk=" + idDisk + "#com");
    }
}