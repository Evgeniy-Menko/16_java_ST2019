package by.menko.finalproject.controller.action.useraction;


import by.menko.finalproject.controller.constantspath.ConstantsPath;
import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.DiskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateAnnouncementCommand extends UserAction {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws PersonalException, ServletException, IOException {
        DiskService service = factory.createService(TypeServiceAndDao.DISK);
        UserInfo user = (UserInfo) request.getSession().getAttribute("authorizedUser");
        String idDisk = request.getParameter("disk");
        Disk disk = service.getDisk(idDisk, user);
        if (user.getIdEntity().equals(disk.getIdUser())) {
            request.setAttribute("disk", disk);
            String message = String.format("User %d updated announcement %s", user.getIdEntity(), idDisk);
            logger.info(message);
            request.getRequestDispatcher(ConstantsPath.EDIT_ANNOUNCEMENT_PAGE).forward(request, response);
        } else {
            throw new PersonalException();
        }
    }
}
