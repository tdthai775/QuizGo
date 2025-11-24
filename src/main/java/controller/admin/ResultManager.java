package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.DTO.ResultDTO;
import model.bo.ExamBO;
import model.bo.ResultBO;

import java.io.IOException;

@WebServlet("/admin/results")
public class ResultManager extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ResultBO resultBO = new ResultBO();
    private ExamBO examBO = new ExamBO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("detail".equals(action)) {
            // Xem chi tiết 1 bài làm
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                ResultDTO resultInfo = resultBO.getResultById(id);
                
                if (resultInfo != null) {
                    request.setAttribute("info", resultInfo);
                    // Lấy chi tiết từng câu đúng sai
                    request.setAttribute("detailList", resultBO.getResultDetails(id));
                    request.getRequestDispatcher("/views/admin/layout.jsp?page=result-detail").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/admin/results");
                }
            } catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/admin/results");
            }
        } else {
            // Xem danh sách (có tìm kiếm)
            String keyword = request.getParameter("keyword");
            String examId = request.getParameter("examId");
            String date = request.getParameter("date");
            
            request.setAttribute("resultList", resultBO.searchResults(keyword, examId, date));
            request.setAttribute("examList", examBO.getAllExams()); // Để hiển thị filter dropdown
            
            // Giữ lại giá trị filter trên form
            request.setAttribute("keyword", keyword);
            request.setAttribute("examId", examId);
            
            request.getRequestDispatcher("/views/admin/layout.jsp?page=results").forward(request, response);
        }
    }
}