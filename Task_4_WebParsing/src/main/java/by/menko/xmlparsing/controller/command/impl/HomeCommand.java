package by.menko.xmlparsing.controller.command.impl;

import by.menko.xmlparsing.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeCommand implements Command {
    /**
     * Go to homepage.
     *
     * @param req  request.
     * @param resp response.
     *
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    public void execute(final HttpServletRequest req,
                        final HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher home = req.getRequestDispatcher("/index.jsp");
        home.forward(req, resp);
    }
}
