package by.menko.finalproject.controller.action.adminaction;

import by.menko.finalproject.controller.action.Command;
import by.menko.finalproject.entity.Complaint;
import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.ComplaintService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ShowComplaintsCommand extends AdminAction {
    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws PersonalException, ServletException, IOException {
        ComplaintService service = factory.createService(TypeServiceAndDao.COMPLAINT);
        Map<UserInfo, Complaint> mapComplaint = service.getAllComplaints();
        List<Disk> diskWithComplaint = service.getDiskWithComplaint(mapComplaint);
        request.setAttribute("listDisk", diskWithComplaint);
        request.setAttribute("mapComplaint", mapComplaint);
        request.getRequestDispatcher("/admin.jsp").forward(request, response);
    }
}
