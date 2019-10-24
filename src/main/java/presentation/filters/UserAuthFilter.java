package presentation.filters;

/*
author: madsbrandt
*/

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "UserAuthFilter", urlPatterns = {"/*"})
public class UserAuthFilter implements Filter {
    private HttpServletRequest httpRequest;

    private static final String[] loginRequiredURLs = {
            "/showUser", "/editUser", "/confirmOrder", "invoice"
    };

    @Override
    public void init (FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        httpRequest = (HttpServletRequest) request;

        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        String loginURI = httpRequest.getContextPath() + "/login";
        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean isLoginPage = httpRequest.getRequestURI().endsWith("login.jsp");
        boolean isRegisterPage = httpRequest.getRequestURI().endsWith("createUser.jsp");

        if (isLoggedIn && (isLoginRequest || isLoginPage || isRegisterPage)) {
            // the user is already logged in and is trying to either access the login page or register page
            // then forward to index
            httpRequest.getRequestDispatcher("/").forward(request, response);

        } else if (!isLoggedIn && isLoginRequired()) {
            // the user is not logged in, and the requested page requires
            // authentication, then forward to the login page
            httpRequest.getRequestDispatcher("/jsp/user/login.jsp").forward(request, response);
        } else {
            // for other requested pages that do not require authentication
            // or the user is already logged in, continue to the destination
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy () {
    }

    private boolean isLoginRequired() {
        String requestURL = httpRequest.getRequestURL().toString();
        for (String loginRequiredURL : loginRequiredURLs) {
            if (requestURL.contains(loginRequiredURL)) {
                return true;
            }
        }
        return false;
    }
}
