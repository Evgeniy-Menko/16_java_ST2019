package by.menko.finalproject.controller.filters;


import by.menko.finalproject.controller.action.Command;
import by.menko.finalproject.controller.action.adminaction.BlockAnnouncementCommand;
import by.menko.finalproject.controller.action.adminaction.DeleteComplaintCommand;
import by.menko.finalproject.controller.action.adminaction.ShowComplaintsCommand;
import by.menko.finalproject.controller.action.adminaction.UnlockAnnouncementCommand;
import by.menko.finalproject.controller.action.forallaction.*;
import by.menko.finalproject.controller.action.useraction.*;
import by.menko.finalproject.controller.constantspath.ConstantsPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class CommandFromUriFilter implements Filter {
    private static Logger logger = LogManager.getLogger();


    /**
     * The <code>doFilter</code> method of the Filter is called by the
     * container each time a request/response pair is passed through the
     * chain due to a client request for a resource at the end of the chain.
     * The FilterChain passed in to this method allows the Filter to pass
     * on the request and response to the next entity in the chain.
     *
     * @param request  the <code>ServletRequest</code> object contains the client's request
     * @param response the <code>ServletResponse</code> object contains the filter's response
     * @param chain    the <code>FilterChain</code> for invoking the next filter or the resource
     *
     * @throws IOException      if an I/O related error has occurred during the processing
     * @throws ServletException if an exception occurs that interferes with the
     *                          filter's normal operation
     * @see UnavailableException
     */

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String contextPath = httpRequest.getContextPath();
            String uri = httpRequest.getRequestURI();
            String message = String.format("Starting of processing of request for URI \"%s\"", uri);
            logger.debug(message);
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
            logger.error("It is impossible to process request");
            request.getRequestDispatcher(ConstantsPath.ERROR_PAGE).forward(request, response);
        }
    }


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
                return new ResultEditProfileCommand();
            case "/addAnnouncement":
                return new AddAnnouncementPageCommand();
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
}
