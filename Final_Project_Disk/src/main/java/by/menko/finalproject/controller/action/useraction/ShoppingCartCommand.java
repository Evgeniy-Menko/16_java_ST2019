package by.menko.finalproject.controller.action.useraction;


import by.menko.finalproject.controller.constantspath.ConstantsPath;
import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.ShoppingCart;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.ShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Map;

public class ShoppingCartCommand extends UserAction {
    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws PersonalException, ServletException, IOException {
        ShoppingCartService service = factory.createService(TypeServiceAndDao.SHOPPING_CART);
        UserInfo user = (UserInfo) request.getSession().getAttribute("authorizedUser");
        Map<ShoppingCart, Disk> mapDisk = service.getShoppingCart(user.getIdEntity());
        request.setAttribute("mapDisk", mapDisk);
        request.getRequestDispatcher(ConstantsPath.SHOPPING_CART_PAGE).forward(request, response);
    }
}
