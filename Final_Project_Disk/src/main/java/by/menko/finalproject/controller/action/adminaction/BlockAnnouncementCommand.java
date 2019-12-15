package by.menko.finalproject.controller.action.adminaction;

import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.ComplaintService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlockAnnouncementCommand extends AdminAction {
    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws PersonalException, ServletException, IOException {
        ComplaintService service = factory.createService(TypeServiceAndDao.COMPLAINT);
        String idDisk = request.getParameter("disk");
        service.blockAnnouncement(idDisk);
        response.sendRedirect("/Panda-Disk/complaints.html");
    }
}
