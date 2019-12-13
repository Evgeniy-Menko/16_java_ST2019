package by.menko.finalproject.controller.filters;


import by.menko.finalproject.controller.action.Command;
import by.menko.finalproject.controller.action.adminaction.AdminAction;
import by.menko.finalproject.controller.action.forallaction.ForAllAction;
import by.menko.finalproject.controller.action.useraction.UserAction;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.Role;

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
        Command command = (Command) httpRequest.getAttribute("action");
        UserInfo user = (UserInfo) httpRequest.getSession().getAttribute("authorizedUser");
        boolean flagClass = false;
        if (user != null && user.getRole() == Role.USER) {
            if (command instanceof UserAction || command instanceof ForAllAction) {
                flagClass = true;
            }
        } else if (user != null && user.getRole() == Role.ADMINISTRATOR) {
            if (command instanceof AdminAction || command instanceof ForAllAction) {
                flagClass = true;
            }
        } else {
            if (command instanceof ForAllAction) {
                flagClass = true;
            }
        }
        if (flagClass) {
            chain.doFilter(request, response);
        } else {
            httpRequest.getRequestDispatcher("/Error.jsp").forward(request, response);
        }
    }

}