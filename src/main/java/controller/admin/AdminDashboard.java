package controller.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.DashboardBO;


@WebServlet("/admin/dashboard")
public class AdminDashboard extends HttpServlet {
    private DashboardBO dashboardBO = new DashboardBO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	request.setAttribute("dashboardStats", dashboardBO.getDashboardStats());
        request.setAttribute("recentActivities", dashboardBO.getRecentActivities(5));
        request.setAttribute("popularExams", dashboardBO.getPopularExams(5));
        
        request.getRequestDispatcher("/views/admin/layout.jsp?page=dashboard").forward(request, response);
    }
}
