package by.menko.finalproject.controller.action.useraction;

import by.menko.finalproject.controller.action.Command;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.ShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAllFromShopCartCommand extends Command {
    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws PersonalException, ServletException, IOException {
        ShoppingCartService service = factory.createService(TypeServiceAndDao.SHOPPING_CART);
        UserInfo user = (UserInfo) request.getSession().getAttribute("authorizedUser");
        service.deleteAll(user.getIdEntity());
        response.sendRedirect("/Panda-Disk/shoppingCart.html");
    }
}
