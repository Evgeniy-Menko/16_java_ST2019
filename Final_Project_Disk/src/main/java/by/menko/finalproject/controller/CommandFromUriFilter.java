package by.menko.finalproject.controller;


import by.menko.finalproject.controller.action.Command;
import by.menko.finalproject.controller.action.useraction.*;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//import org.apache.log4j.Logger;

public class CommandFromUriFilter implements Filter {
//	private static Logger logger = Logger.getLogger(ActionFromUriFilter.class);

    private static Map<String, Command> actions = new ConcurrentHashMap<>();

    static {
        actions.put("/", new HomeCommand());
        actions.put("/home", new HomeCommand());
        actions.put("/login", new LoginCommand());
        actions.put("/logout", new LogoutCommand());
        actions.put("/search", new SearchCommand());
        actions.put("/registr", new RegistrationCommand());
        actions.put("/registration", new RegistrPageCommand());
        actions.put("/profile", new ProfileCommand());
        actions.put("/editProfile", new EditProfileCommand());
        actions.put("/editProfileResult", new ResultEditCommand());
        actions.put("/addAnnouncement", new AddAnnouncementCommand());
        actions.put("/announcementResult", new ResultAddAnnouncementCommand());
        actions.put("/myAnnouncements", new MyAnnouncementCommand());
        actions.put("/updateAnnouncement", new UpdateAnnouncementCommand());
        actions.put("/announcementEditResult", new AnnouncementEditResultCommand());
        actions.put("/showDisk", new ShowDiskCommand());
        actions.put("/addComment", new AddCommentCommand());
        actions.put("/deleteComment", new DeleteCommentCommand());
        actions.put("/addShoppingCart", new AddShoppingCartCommand());
        actions.put("/shoppingCart", new ShoppingCartCommand());
        actions.put("/deleteFromShopCart", new DeleteFromShopCartCommand());
       actions.put("/deleteAll", new DeleteAllFromShopCartCommand());

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

                Command command = actions.get(actionName);
                httpRequest.setAttribute("action", command);
                chain.doFilter(request, response);

        } else {
            //	logger.error("It is impossible to use HTTP filter");

        }
    }

    @Override
    public void destroy() {
    }
}