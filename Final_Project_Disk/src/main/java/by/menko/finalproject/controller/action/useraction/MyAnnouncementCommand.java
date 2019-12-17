package by.menko.finalproject.controller.action.useraction;


import by.menko.finalproject.controller.constantspath.ConstantsPath;
import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.DiskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MyAnnouncementCommand extends UserAction {
    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws PersonalException, ServletException, IOException {
        DiskService service = factory.createService(TypeServiceAndDao.DISK);
        UserInfo user = (UserInfo) request.getSession().getAttribute("authorizedUser");
        List<Disk> resultList = service.getAllDiskByIdUser(user.getIdEntity());
        request.setAttribute("listDisk", resultList);
        request.getRequestDispatcher(ConstantsPath.MY_ANNOUNCEMENT_PAGE).forward(request, response);
    }
}
