package by.menko.xmlparsing.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class DoFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String home = req.getContextPath() + "/home";
        String url = req.getRequestURI();
        String result = req.getContextPath() + "/home?action=result";
        if (!home.equals(url) && !result.equals(url)) {
            resp.sendRedirect(home);
        } else {
            filterChain.doFilter(req, resp);
        }
    }
}
