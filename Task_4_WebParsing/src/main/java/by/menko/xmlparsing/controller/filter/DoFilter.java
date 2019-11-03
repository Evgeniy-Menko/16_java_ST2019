package by.menko.xmlparsing.controller.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class DoFilter implements Filter {
    /**
     * Filter for redirecting invalid requests.
     * @param servletRequest request.
     * @param servletResponse response.
     * @param filterChain chain.
     * @throws IOException .
     * @throws ServletException .
     */
    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String home = req.getContextPath() + "/home";
        String url = req.getRequestURI();
        String result = req.getContextPath() + "/home?action=result";
        if (home.equals(url) || result.equals(url)) {
            filterChain.doFilter(req, resp);
        } else {
            resp.sendRedirect(home);
        }
    }
}
