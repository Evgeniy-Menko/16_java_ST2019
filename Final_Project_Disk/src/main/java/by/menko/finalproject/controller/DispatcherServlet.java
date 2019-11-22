package by.menko.finalproject.controller;

import by.menko.finalproject.controller.action.Action;
import by.menko.finalproject.controller.action.ActionManager;
import by.menko.finalproject.controller.action.ActionManagerFactory;
import by.menko.finalproject.dao.impl.TransactionFactoryImpl;
import by.menko.finalproject.dao.pool.ConnectionPool;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.ServiceFactory;
import by.menko.finalproject.service.impl.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    public void init() {
        try {
            ConnectionPool.getInstance();
        } catch (PersonalException e) {
            destroy();
        }
    }

    public ServiceFactory getFactory() throws PersonalException {
        return new ServiceFactoryImpl(new TransactionFactoryImpl());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Action action = (Action) request.getAttribute("action");
        try {
            ActionManager actionManager = ActionManagerFactory.getManager(getFactory());

            Action.Forward forward = actionManager.execute(action, request, response);
            actionManager.close();
            if (request.getSession() != null && forward != null && !forward.getAttributes().isEmpty()) {
                request.getSession().setAttribute("redirectedData", forward.getAttributes());
            }
            String requestedUri = request.getRequestURI();
            if (forward != null && forward.isRedirect()) {
                String redirectedUri = request.getContextPath() + forward.getForward();
                //	logger.debug(String.format("Request for URI \"%s\" id redirected to URI \"%s\"", requestedUri, redirectedUri));
                response.sendRedirect(redirectedUri);
            } else {
                String jspPage;
                if (forward != null) {
                    jspPage = forward.getForward();
                } else {
                    jspPage = action.getName() + ".jsp";
                }
                jspPage = "/" + jspPage;
                //	logger.debug(String.format("Request for URI \"%s\" is forwarded to JSP \"%s\"", requestedUri, jspPage));
                getServletContext().getRequestDispatcher(jspPage).forward(request, response);
            }
        } catch (PersonalException e) {
            e.printStackTrace();
        }
    }
}
