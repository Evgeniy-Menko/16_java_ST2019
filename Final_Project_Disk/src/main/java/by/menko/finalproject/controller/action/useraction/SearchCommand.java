package by.menko.finalproject.controller.action.useraction;

import by.menko.finalproject.controller.action.Command;

import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.exception.ServicePersonalException;
import by.menko.finalproject.service.DiskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class SearchCommand extends Command {
    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws PersonalException, ServletException, IOException {
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
            request.getRequestDispatcher("/search.jsp").forward(request, response);
        } catch (ServicePersonalException e) {
            e.printStackTrace();
        }
    }
}
