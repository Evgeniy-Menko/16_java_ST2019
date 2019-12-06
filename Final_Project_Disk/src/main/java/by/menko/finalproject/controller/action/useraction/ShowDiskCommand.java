package by.menko.finalproject.controller.action.useraction;

import by.menko.finalproject.controller.action.Command;
import by.menko.finalproject.entity.Comment;
import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.ShoppingCart;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.CommentService;
import by.menko.finalproject.service.DiskService;
import by.menko.finalproject.service.ShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ShowDiskCommand extends Command {
    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws PersonalException, ServletException, IOException {
        DiskService service = factory.createService(TypeServiceAndDao.DISK);
        CommentService serviceComment = factory.createService(TypeServiceAndDao.COMMENT);
        ShoppingCartService serviceShopCart = factory.createService(TypeServiceAndDao.SHOPPING_CART);
        UserInfo user = (UserInfo) request.getSession().getAttribute("authorizedUser");
        String idDisk = request.getParameter("disk");
        Disk disk = service.getDisk(idDisk);
        Map<UserInfo, Comment> mapComment = serviceComment.getComment(disk.getIdEntity());
        List<ShoppingCart> listShopCart = serviceShopCart.getAllDiskFromShopCart(user.getIdEntity());
        request.setAttribute("shopCart", listShopCart);
        request.setAttribute("disk", disk);
        request.setAttribute("mapComment", mapComment);
        request.getRequestDispatcher("/disk.jsp").forward(request, response);
    }
}
