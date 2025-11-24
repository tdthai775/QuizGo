package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.UserBO;

import java.io.IOException;

@WebServlet("/admin/users")
public class UserManager extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserBO userBO = new UserBO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "delete":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    userBO.deleteUser(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                response.sendRedirect(request.getContextPath() + "/admin/users");
                break;
            default: // List
                request.setAttribute("userList", userBO.getAllUsers());
                request.getRequestDispatcher("/views/admin/layout.jsp?page=manage-users").forward(request, response);
                break;
        }
    }
}
