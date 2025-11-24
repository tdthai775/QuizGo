package controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.bean.*;
import model.bean.DTO.QuestionResultDTO;
import model.bo.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/user/exam"})
public class ExamServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ExamBO examBO = new ExamBO();
    private SubmissionBO submissionBO = new SubmissionBO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                showExamList(request, response);
                break;
            case "take":
                showTakeExam(request, response);
                break;
            case "history":
                showHistory(request, response);
                break;
            case "result":
                showResultDetail(request, response);
                break;
            case "grading":
                request.getRequestDispatcher("/views/user/layout.jsp?page=grading").forward(request, response);
                break;
            case "check-status":
                checkGradingStatus(request, response);
                break;
            default:
                showExamList(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("submit".equals(action)) {
            processSubmitExam(request, response);
        } else {
            doGet(request, response);
        }
    }

   
    // 1. Hiển thị danh sách đề thi
    private void showExamList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Exam> list = examBO.getExamList();
        request.setAttribute("examList", list);
        request.getRequestDispatcher("/views/user/layout.jsp?page=exam-list").forward(request, response);
    }

    // 2. Vào trang làm bài thi
    private void showTakeExam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int examId = Integer.parseInt(request.getParameter("examId"));
            
            // Lấy thông tin đề thi
            Exam exam = examBO.getExamForTaking(examId);
            if (exam == null) {
                response.sendRedirect(request.getContextPath() + "/user/exam?action=list");
                return;
            }

            // Lấy danh sách câu hỏi và đáp án để hiển thị
            ArrayList<Question> questions = examBO.getQuestions(examId);
            
            request.setAttribute("exam", exam);
            request.setAttribute("questionList", questions);
            
            request.getRequestDispatcher("/views/user/layout.jsp?page=take").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/user/exam?action=list");
        }
    }

    // 3. Xử lý Nộp bài 
    private void processSubmitExam(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        
        // Kiểm tra session user 
        Integer userId = (Integer) session.getAttribute("userID");
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/views/auth/login.jsp");
            return;
        }

        try {
            int examId = Integer.parseInt(request.getParameter("examId"));
            
            // Lấy lại danh sách câu hỏi để biết cần lấy parameter nào
            ArrayList<Question> questions = examBO.getQuestions(examId);
            List<SubmissionAnswer> answers = new ArrayList<>();

            // Quét từng câu hỏi để lấy đáp án user chọn từ form
            for (Question q : questions) {
                String paramName = "q_" + q.getId(); // Tên input radio là q_1, q_2...
                String selectedId = request.getParameter(paramName);
                
                Integer choiceId = null;
                if (selectedId != null && !selectedId.isEmpty()) {
                    choiceId = Integer.parseInt(selectedId);
                }
                
                // Tạo đối tượng câu trả lời (dù user không chọn cũng vẫn lưu với choiceId = null)
                answers.add(new SubmissionAnswer(0, 0, q.getId(), choiceId));
            }

            // Gọi BO xử lý logic nộp bài (Transaction + Tạo Job chấm điểm)
            int submissionId = submissionBO.submitExam(userId, examId, answers);

            if (submissionId > 0) {
                // Nộp xong -> Chuyển sang trang "Đang chấm..." để Worker chạy ngầm
            	response.sendRedirect(request.getContextPath() + "/user/exam?action=grading&id=" + submissionId);
            } else {
                request.setAttribute("errorMessage", "Nộp bài thất bại! Có lỗi hệ thống.");
                showExamList(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/user/exam?action=list");
        }
    }

    // 4. Xem lịch sử thi
    private void showHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userID");
        
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/views/auth/login.jsp");
            return;
        }
        
        // Lấy danh sách bài đã nộp
        List<Submission> list = submissionBO.getUserHistory(userId);
        request.setAttribute("submissionList", list);
        
        request.getRequestDispatcher("/views/user/layout.jsp?page=history").forward(request, response);
    }

    // 5. Xem chi tiết kết quả (Sau khi đã chấm xong)
    private void showResultDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int submissionId = Integer.parseInt(request.getParameter("id"));
            
            // Lấy thông tin chung (Điểm, ngày nộp, tên đề...)
            Submission submission = submissionBO.getSubmission(submissionId);
            
            // Kiểm tra nếu bài chưa chấm xong (status != done) thì không cho xem chi tiết
            if (submission == null || !"done".equals(submission.getStatus())) {
                response.sendRedirect(request.getContextPath() + "/user/exam?action=history");
                return;
            }

            // Lấy danh sách chi tiết từng câu (DTO: Câu hỏi + Đáp án User + Đáp án Đúng)
            List<QuestionResultDTO> details = submissionBO.getResultDetail(submissionId);
            
            request.setAttribute("submission", submission);
            request.setAttribute("detailList", details);
            
            request.getRequestDispatcher("/views/user/layout.jsp?page=result").forward(request, response);
            
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/user/exam?action=history");
        }
    }
    
    // 6. (Optional) API cho AJAX check trạng thái
    private void checkGradingStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        
        try {
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.isEmpty()) {
                int submissionId = Integer.parseInt(idStr);
                // Gọi BO lấy thông tin bài thi
                Submission sub = submissionBO.getSubmission(submissionId);
                
                if (sub != null) {
                    response.getWriter().write(sub.getStatus());
                } else {
                    response.getWriter().write("error");
                }
            } else {
                response.getWriter().write("done");
            }
        } catch (Exception e) {
            response.getWriter().write("error");
        }
    }
}