package by.menko.finalproject.controller.filters;


import by.menko.finalproject.entity.UserInfo;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SecurityFilter implements Filter {


    /**
     * The <code>doFilter</code> method of the Filter is called by the
     * container each time a request/response pair is passed through the
     * chain due to a client request for a resource at the end of the chain.
     * The FilterChain passed in to this method allows the Filter to pass
     * on the request and response to the next entity in the chain.
     *
     * <p>A typical implementation of this method would follow the following
     * pattern:
     * <ol>
     * <li>Examine the request
     * <li>Optionally wrap the request object with a custom implementation to
     * filter content or headers for input filtering
     * <li>Optionally wrap the response object with a custom implementation to
     * filter content or headers for output filtering
     * <li>
     * <ul>
     * <li><strong>Either</strong> invoke the next entity in the chain
     * using the FilterChain object
     * (<code>chain.doFilter()</code>),
     * <li><strong>or</strong> not pass on the request/response pair to
     * the next entity in the filter chain to
     * block the request processing
     * </ul>
     * <li>Directly set headers on the response after invocation of the
     * next entity in the filter chain.
     * </ol>
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
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
            IOException, ServletException {
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
        UserInfo user = (UserInfo) httpRequest.getSession().getAttribute("authorizedUser");

        if (user != null && user.getRole().getName().equals("User")) {
            if (!secureForUser(actionName)) {
                httpRequest.getRequestDispatcher("/Error.jsp").forward(request, response);
            }
        } else if (user != null && user.getRole().getName().equals("Administrator")) {
            if (!secureForAdmin(actionName)) {
                httpRequest.getRequestDispatcher("/Error.jsp").forward(request, response);
            }
        } else {
            if (!secureForAll(actionName)) {
                httpRequest.getRequestDispatcher("/Error.jsp").forward(request, response);
            }
        }

        chain.doFilter(request, response);

    }


    private Boolean secureForAll(String action) {
        switch (action) {
            case "/":
            case "/home":
            case "/login":
            case "/search":
            case "/registr":
            case "/registration":
            case "/showDisk":
                return true;
            default:
                return false;
        }
    }

    private Boolean secureForUser(String action) {
        switch (action) {
            case "/":
            case "/home":
            case "/logout":
            case "/search":
            case "/profile":
            case "/editProfile":
            case "/editProfileResult":
            case "/addAnnouncement":
            case "/announcementResult":
            case "/myAnnouncements":
            case "/updateAnnouncement":
            case "/announcementEditResult":
            case "/showDisk":
            case "/addComment":
            case "/deleteComment":
            case "/addShoppingCart":
            case "/shoppingCart":
            case "/deleteFromShopCart":
            case "/deleteAll":
            case "/addComplaint":
            case "/deleteDisk":
                return true;
            default:
                return false;
        }
    }

    private Boolean secureForAdmin(String action) {
        switch (action) {
            case "/":
            case "/home":
            case "/logout":
            case "/search":
            case "/complaints":
            case "/deleteComplaint":
            case "/block":
            case "/unlock":
            case "/showDisk":
                return true;
            default:
                return false;
        }
    }
}