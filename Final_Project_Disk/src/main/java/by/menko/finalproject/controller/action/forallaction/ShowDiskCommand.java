package by.menko.finalproject.controller.action.forallaction;

import by.menko.finalproject.controller.constantspath.ConstantsPath;
import by.menko.finalproject.entity.Comment;
import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.ShoppingCart;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.CommentService;
import by.menko.finalproject.service.DiskService;
import by.menko.finalproject.service.ShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ShowDiskCommand extends ForAllAction {
    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws PersonalException, ServletException, IOException {
        DiskService service = factory.createService(TypeServiceAndDao.DISK);
        CommentService serviceComment = factory.createService(TypeServiceAndDao.COMMENT);
        ShoppingCartService serviceShopCart = factory.createService(TypeServiceAndDao.SHOPPING_CART);
        UserInfo user = (UserInfo) request.getSession().getAttribute("authorizedUser");
        String idDisk = request.getParameter("disk");
        Disk disk = service.getDisk(idDisk, user);
        Map<Comment, UserInfo> mapComment = serviceComment.getComment(disk.getIdEntity());
        if (user != null) {
            List<ShoppingCart> listShopCart = serviceShopCart.getAllDiskFromShopCart(user.getIdEntity());
            request.setAttribute("shopCart", listShopCart);
        }
        request.setAttribute("disk", disk);
        request.setAttribute("mapComment", mapComment);
        request.getRequestDispatcher(ConstantsPath.SHOW_DISK).forward(request, response);
    }
}
