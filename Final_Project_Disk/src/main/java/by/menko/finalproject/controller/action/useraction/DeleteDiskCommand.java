package by.menko.finalproject.controller.action.useraction;


import by.menko.finalproject.controller.constantspath.ConstantsPath;
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

public class DeleteDiskCommand extends UserAction {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws PersonalException, ServletException, IOException {
        DiskService service = factory.createService(TypeServiceAndDao.DISK);
        String idDisk = request.getParameter("disk");
        UserInfo user = (UserInfo) request.getSession().getAttribute("authorizedUser");
        service.deleteDisk(idDisk, user.getIdEntity());
        logger.info(String.format("User %d deleted announcement %s", user.getIdEntity(), idDisk));
        response.sendRedirect(request.getContextPath() + ConstantsPath.MY_ANNOUNCEMENT);
    }
}
