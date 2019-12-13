package by.menko.finalproject.controller.filters;


import by.menko.finalproject.controller.action.Command;
import by.menko.finalproject.controller.action.adminaction.BlockAnnouncementCommand;
import by.menko.finalproject.controller.action.adminaction.DeleteComplaintCommand;
import by.menko.finalproject.controller.action.adminaction.ShowComplaintsCommand;
import by.menko.finalproject.controller.action.adminaction.UnlockAnnouncementCommand;
import by.menko.finalproject.controller.action.forallaction.*;
import by.menko.finalproject.controller.action.useraction.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class CommandFromUriFilter implements Filter {

    private Command getCommand(String action) {
        switch (action) {
            case "/":
                return new HomeCommand();
            case "/home":
                return new HomeCommand();
            case "/login":
                return new LoginCommand();
            case "/logout":
                return new LogoutCommand();
            case "/search":
                return new SearchCommand();
            case "/registr":
                return new RegistrationCommand();
            case "/registration":
                return new RegistrPageCommand();
            case "/profile":
                return new ProfileCommand();
            case "/editProfile":
                return new EditProfileCommand();
            case "/editProfileResult":
                return new ResultEditCommand();
            case "/addAnnouncement":
                return new AddAnnouncementCommand();
            case "/announcementResult":
                return new ResultAddAnnouncementCommand();
            case "/myAnnouncements":
                return new MyAnnouncementCommand();
            case "/updateAnnouncement":
                return new UpdateAnnouncementCommand();
            case "/announcementEditResult":
                return new AnnouncementEditResultCommand();
            case "/showDisk":
                return new ShowDiskCommand();
            case "/addComment":
                return new AddCommentCommand();
            case "/deleteComment":
                return new DeleteCommentCommand();
            case "/addShoppingCart":
                return new AddShoppingCartCommand();
            case "/shoppingCart":
                return new ShoppingCartCommand();
            case "/deleteFromShopCart":
                return new DeleteFromShopCartCommand();
            case "/deleteAll":
                return new DeleteAllFromShopCartCommand();
            case "/addComplaint":
                return new AddComplaintCommand();
            case "/complaints":
                return new ShowComplaintsCommand();
            case "/deleteComplaint":
                return new DeleteComplaintCommand();
            case "/deleteDisk":
                return new DeleteDiskCommand();
            case "/block":
                return new BlockAnnouncementCommand();
            case "/unlock":
                return new UnlockAnnouncementCommand();
            default:
                return new HomeCommand();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String contextPath = httpRequest.getContextPath();
            String uri = httpRequest.getRequestURI();
            //	logger.debug(String.format("Starting of processing of request for URI \"%s\"", uri));
            int beginAction = contextPath.length();
            int endAction = uri.lastIndexOf('.');
            String actionName;
            if (endAction >= 0) {
                actionName = uri.substring(beginAction, endAction);
            } else {
                actionName = uri.substring(beginAction);
            }
            Command command = getCommand(actionName);
            httpRequest.setAttribute("action", command);
            chain.doFilter(request, response);

        } else {
            request.getRequestDispatcher("/Error.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
