package by.menko.finalproject.controller.action.adminaction;

import by.menko.finalproject.controller.action.Command;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.ComplaintService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteComplaintCommand extends Command {
    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws PersonalException, ServletException, IOException {
        ComplaintService service = factory.createService(TypeServiceAndDao.COMPLAINT);
        String idComplaint = request.getParameter("complaint");
        service.deleteComplaint(idComplaint);
        response.sendRedirect("/Panda-Disk/complaints.html");
    }
}
