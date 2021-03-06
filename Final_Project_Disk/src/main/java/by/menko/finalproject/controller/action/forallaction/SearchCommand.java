package by.menko.finalproject.controller.action.forallaction;


import by.menko.finalproject.controller.constantspath.ConstantsPath;
import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.exception.ServicePersonalException;
import by.menko.finalproject.service.DiskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class SearchCommand extends ForAllAction {
    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws PersonalException, ServletException, IOException {
        DiskService service = factory.createService(TypeServiceAndDao.DISK);
        String type = request.getParameter("type");
        String genre = request.getParameter("genre");
        String priceFrom = request.getParameter("priceFrom");
        String priceTo = request.getParameter("priceTo");
        String dateIn = request.getParameter("dateIn");
        String dateTo = request.getParameter("dateTo");
        try {
            List<Disk> resultList = service.getDisk(type, genre, priceFrom, priceTo, dateIn, dateTo);
            request.setAttribute("listDisk", resultList);
            request.getRequestDispatcher(ConstantsPath.SEARCH_PAGE).forward(request, response);
        } catch (ServicePersonalException e) {
            request.setAttribute("error", "errorSearch");
            request.getRequestDispatcher(ConstantsPath.SEARCH_PAGE).forward(request, response);
        }
    }
}
