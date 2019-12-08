package by.menko.finalproject.controller.action.forallaction;

import by.menko.finalproject.controller.action.Command;
import by.menko.finalproject.entity.Catalog;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.CatalogService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MenuCommand extends Command {
    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws PersonalException, ServletException, IOException {
        CatalogService service = factory.createService(TypeServiceAndDao.CATALOG);

        List<Catalog> catalog = service.getCatalog();
        request.setAttribute("catalog", catalog);

    }
}
