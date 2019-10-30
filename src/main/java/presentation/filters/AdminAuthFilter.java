package presentation.filters;/*
author: madsbrandt
*/

import logic.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminAuthFilter", urlPatterns = {"/jsp/admin/*"})
public class AdminAuthFilter implements Filter {

    public void destroy () {
    }

    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        User user = (User) session.getAttribute("user");
        boolean isAdmin = user.isAdmin();

        if (isAdmin) {
            chain.doFilter(request, response);
        } else {
            httpRequest.getRequestDispatcher("/").forward(request, response);
        }
    }

    public void init (FilterConfig config) throws ServletException {

    }

}
