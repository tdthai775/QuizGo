package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.Exam;
import model.bo.ExamBO;

import java.io.IOException;

@WebServlet("/admin/exams")
public class ExamManager extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ExamBO examBO = new ExamBO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "create":
                    // BÁO CHO LAYOUT BIẾT LÀ HIỆN TRANG TẠO MỚI RIÊNG BIỆT
                    request.setAttribute("page", "exam-create"); 
                    request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
                    break;
                    
                case "edit":
                    // LOGIC CHO TRANG SỬA (GỒM CẢ SỬA ĐỀ + QUẢN LÝ CÂU HỎI)
                    int id = Integer.parseInt(request.getParameter("id"));
                    Exam ex = examBO.getExamById(id);
                    
                    if (ex != null) {
                        request.setAttribute("exam", ex);
                        
                        // QUAN TRỌNG: Lấy luôn danh sách câu hỏi của đề này để hiển thị bên phải
                        request.setAttribute("questionList", examBO.getQuestions(id));
                        
                        // BÁO CHO LAYOUT BIẾT LÀ HIỆN TRANG EDIT
                        request.setAttribute("page", "exam-edit");
                        request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/admin/exams");
                    }
                    break;
                    
                case "delete":
                    int deleteId = Integer.parseInt(request.getParameter("id"));
                    examBO.deleteExam(deleteId);
                    response.sendRedirect(request.getContextPath() + "/admin/exams");
                    break;
                    
                default: // Danh sách
                    request.setAttribute("page", "manage-exams");
                    request.setAttribute("examList", examBO.getAllExams());
                    request.getRequestDispatcher("/views/admin/layout.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/exams");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        
        // Kiểm tra session admin
        if (session == null || session.getAttribute("userID") == null) {
            response.sendRedirect(request.getContextPath() + "/views/auth/login.jsp");
            return;
        }
        int userId = (Integer) session.getAttribute("userID");

        try {
            String action = request.getParameter("action");
            String title = request.getParameter("title");
            String desc = request.getParameter("description");
            int duration = Integer.parseInt(request.getParameter("durationMinutes"));
            int status = Integer.parseInt(request.getParameter("status"));

            if ("create".equals(action)) {
                Exam ex = new Exam(0, title, desc, duration, null, userId, status);
                examBO.createExam(ex);
                // Tạo xong quay về danh sách để thấy đề mới
                response.sendRedirect(request.getContextPath() + "/admin/exams");
                
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Exam ex = new Exam(id, title, desc, duration, null, userId, status);
                examBO.updateExam(ex);
                // Update xong thì reload lại TRANG EDIT để user thấy thay đổi và làm việc tiếp
                response.sendRedirect(request.getContextPath() + "/admin/exams?action=edit&id=" + id);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/exams");
        }
    }
}