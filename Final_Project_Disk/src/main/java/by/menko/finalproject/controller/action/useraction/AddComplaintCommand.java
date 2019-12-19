package by.menko.finalproject.controller.action.useraction;


import by.menko.finalproject.controller.constantspath.ConstantsPath;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.ComplaintService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddComplaintCommand extends UserAction {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws PersonalException, ServletException, IOException {
        ComplaintService service = factory.createService(TypeServiceAndDao.COMPLAINT);
        UserInfo user = (UserInfo) request.getSession().getAttribute("authorizedUser");
        String idUser = request.getParameter("idUser");
        String complaint = request.getParameter("complaint");
        String idDisk = request.getParameter("idDisk");
        service.addComplaint(idDisk, idUser, complaint, user.getIdEntity());
        String message = String.format("User %d wrote a complaint about the advertisement %s", user.getIdEntity(), idDisk);
        logger.info(message);
        response.sendRedirect(request.getContextPath() + ConstantsPath.SHOW_DISK_WITH_PARAMETER + idDisk);
    }
}
