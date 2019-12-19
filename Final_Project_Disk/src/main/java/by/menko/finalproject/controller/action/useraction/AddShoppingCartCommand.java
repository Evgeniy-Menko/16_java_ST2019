package by.menko.finalproject.controller.action.useraction;


import by.menko.finalproject.controller.constantspath.ConstantsPath;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.ShoppingCartService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddShoppingCartCommand extends UserAction {
    private static Logger logger = LogManager.getLogger();
    @Override
    public void exec(final HttpServletRequest request,final  HttpServletResponse response) throws PersonalException, ServletException, IOException {
        ShoppingCartService service = factory.createService(TypeServiceAndDao.SHOPPING_CART);
        UserInfo user = (UserInfo) request.getSession().getAttribute("authorizedUser");
        String idDisk = request.getParameter("disk");
        service.addShoppingCart(idDisk, user.getIdEntity());
        String message = String.format("User %d added announcement %s in shopping cart", user.getIdEntity(), idDisk);
        logger.info(message);
        response.sendRedirect(request.getContextPath()+ ConstantsPath.SHOW_DISK_WITH_PARAMETER + idDisk);
    }
}
