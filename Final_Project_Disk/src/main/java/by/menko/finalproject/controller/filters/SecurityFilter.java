package by.menko.finalproject.controller.filters;


import by.menko.finalproject.controller.action.Command;
import by.menko.finalproject.controller.action.adminaction.*;
import by.menko.finalproject.controller.action.forallaction.*;
import by.menko.finalproject.controller.action.useraction.*;
import by.menko.finalproject.controller.constantspath.ConstantsPath;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SecurityFilter implements Filter {

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
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws
            IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Command command = (Command) httpRequest.getAttribute("action");
        UserInfo user = (UserInfo) httpRequest.getSession().getAttribute("authorizedUser");
        boolean flagClass = false;
        if (user != null && user.getRole() == Role.USER) {
            if (userAction.containsKey(command.getClass())) {
                flagClass = true;
            }
        } else if (user != null && user.getRole() == Role.ADMINISTRATOR) {
            if (adminAction.containsKey(command.getClass())) {
                flagClass = true;
            }
        } else {
            if (forAllAction.containsKey(command.getClass())) {
                flagClass = true;
            }
        }
        if (flagClass) {
            chain.doFilter(request, response);
        } else {
            logger.error("Access error!");
            httpRequest.getRequestDispatcher(ConstantsPath.ERROR_PAGE).forward(request, response);
        }
    }

    private static Map<Class<? extends Command>, String> adminAction = new ConcurrentHashMap<>();
    private static Map<Class<? extends Command>, String> userAction = new ConcurrentHashMap<>();
    private static Map<Class<? extends Command>, String> forAllAction = new ConcurrentHashMap<>();


    static {
        //adminAction
        adminAction.put(BlockAnnouncementCommand.class, "");
        adminAction.put(DeleteComplaintCommand.class, "");
        adminAction.put(ShowComplaintsCommand.class, "");
        adminAction.put(UnlockAnnouncementCommand.class, "");
        adminAction.put(HomeCommand.class, "");
        adminAction.put(LoginCommand.class, "");
        adminAction.put(LogoutCommand.class, "");
        adminAction.put(MenuCommand.class, "");
        adminAction.put(RegistrationCommand.class, "");
        adminAction.put(RegistrPageCommand.class, "");
        adminAction.put(SearchCommand.class, "");
        adminAction.put(ShowDiskCommand.class, "");
        //userAction
        userAction.put(AddAnnouncementCommand.class, "");
        userAction.put(AddCommentCommand.class, "");
        userAction.put(AddComplaintCommand.class, "");
        userAction.put(AddShoppingCartCommand.class, "");
        userAction.put(AnnouncementEditResultCommand.class, "");
        userAction.put(DeleteAllFromShopCartCommand.class, "");
        userAction.put(DeleteCommentCommand.class, "");
        userAction.put(DeleteDiskCommand.class, "");
        userAction.put(DeleteFromShopCartCommand.class, "");
        userAction.put(EditProfileCommand.class, "");
        userAction.put(MyAnnouncementCommand.class, "");
        userAction.put(ProfileCommand.class, "");
        userAction.put(ResultAddAnnouncementCommand.class, "");
        userAction.put(ResultEditCommand.class, "");
        userAction.put(ShoppingCartCommand.class, "");
        userAction.put(UpdateAnnouncementCommand.class, "");
        userAction.put(HomeCommand.class, "");
        userAction.put(LoginCommand.class, "");
        userAction.put(LogoutCommand.class, "");
        userAction.put(MenuCommand.class, "");
        userAction.put(RegistrationCommand.class, "");
        userAction.put(RegistrPageCommand.class, "");
        userAction.put(SearchCommand.class, "");
        userAction.put(ShowDiskCommand.class, "");
        //for all action
        forAllAction.put(HomeCommand.class, "");
        forAllAction.put(LoginCommand.class, "");
        forAllAction.put(LogoutCommand.class, "");
        forAllAction.put(MenuCommand.class, "");
        forAllAction.put(RegistrationCommand.class, "");
        forAllAction.put(RegistrPageCommand.class, "");
        forAllAction.put(SearchCommand.class, "");
        forAllAction.put(ShowDiskCommand.class, "");

    }

}