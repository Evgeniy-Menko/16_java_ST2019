package by.menko.xmlparsing.controller;

import by.menko.xmlparsing.controller.command.Command;
import by.menko.xmlparsing.controller.command.CommandFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@MultipartConfig
@WebServlet("/home")
public class MyServlet extends HttpServlet {
    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Object log4j2.
     */
    private static Logger log = LogManager.getLogger();

    /**
     * doPost.
     *
     * @param req  request.
     * @param resp response.
     *
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }


    /**
     * doGet.
     *
     * @param req  request.
     * @param resp response.
     *
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }

    private void process(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            if (action == null) {
                action = "home";
            }
            CommandFactory factory = CommandFactory.getInstance();
            Command command = factory.getCommand(action);
            command.execute(req, resp);
        } catch (IOException e) {
            log.error("Error reading file: ", e);
            String error = "Error reading file, please go to the home page!";
            req.setAttribute("result", error);
            RequestDispatcher errorPage = req
                    .getRequestDispatcher("/Error.jsp");
            errorPage.forward(req, resp);
        }
    }
}
