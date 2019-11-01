package by.menko.xmlparsing.controller;

import by.menko.xmlparsing.controller.command.Command;
import by.menko.xmlparsing.controller.command.CommandFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final long serialVersionUID = 1L;
    private static Logger log = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String action = req.getParameter("action");
            if (action == null) {
                action = "home";
            }
            CommandFactory factory = CommandFactory.getInstance();
            Command command = factory.getCommand(action);
            command.execute(req, resp);
        } catch (ServletException | IOException e) {
            System.out.println(e);
        }
    }
}
