package controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/user/*"})
public class AuthFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        HttpSession session = req.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        
        if (!isLoggedIn) {
            res.sendRedirect(req.getContextPath() + "/views/auth/login.jsp");
            return;
        }
        
        String role = (String) session.getAttribute("role");
        String path = req.getServletPath();

        if (path.startsWith("/admin") && !"admin".equals(role)) {
            res.sendRedirect(req.getContextPath() + "/user/exam?action=list");
            return;
        }

        chain.doFilter(request, response);
    }
}