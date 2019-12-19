package by.menko.finalproject.controller.action.adminaction;

import by.menko.finalproject.controller.constantspath.ConstantsPath;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.ComplaintService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteComplaintCommand extends AdminAction {
    private static Logger logger = LogManager.getLogger();
    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws PersonalException, ServletException, IOException {
        ComplaintService service = factory.createService(TypeServiceAndDao.COMPLAINT);
        String idComplaint = request.getParameter("complaint");
        service.deleteComplaint(idComplaint);
        String message = String.format("Admin delete complaint with id %s", idComplaint);
        logger.info(message);
        response.sendRedirect(request.getContextPath() + ConstantsPath.COMPLAINTS);
    }
}
