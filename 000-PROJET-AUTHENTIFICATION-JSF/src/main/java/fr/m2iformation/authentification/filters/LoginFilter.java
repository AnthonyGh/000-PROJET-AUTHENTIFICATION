package fr.m2iformation.authentification.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2iformation.authentification.mbeans.LoginManager;

/**
 *
 * @author Joachim
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LoginManager loginManager = (LoginManager) ((HttpServletRequest) request).getSession().getAttribute("login");
        if (loginManager == null || !loginManager.isIsLogged()) {
            String contextPath = ((HttpServletRequest) request).getContextPath();
            ((HttpServletResponse) response).sendRedirect(contextPath + "/login.xhtml");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
