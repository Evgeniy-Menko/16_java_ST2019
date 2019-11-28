package by.menko.finalproject.controller;


import by.menko.finalproject.controller.action.Command;
import by.menko.finalproject.controller.action.useraction.LoginCommand;
import by.menko.finalproject.controller.action.useraction.LogoutCommand;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//import org.apache.log4j.Logger;

public class ActionFromUriFilter implements Filter {
//	private static Logger logger = Logger.getLogger(ActionFromUriFilter.class);

    private static Map<String, Command> actions = new ConcurrentHashMap<>();

    static {
        actions.put("/", new LoginCommand());
        actions.put("/login", new LoginCommand());
        actions.put("/logout", new LogoutCommand());
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
            try {
                Command command = actions.get(actionName);
                httpRequest.setAttribute("action", command);
                chain.doFilter(request, response);
            } catch (NullPointerException e) {
                //		logger.error("It is impossible to create action handler object", e);

            }
        } else {
            //	logger.error("It is impossible to use HTTP filter");

        }
    }

    @Override
    public void destroy() {
    }
}
