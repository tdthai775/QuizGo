package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.Choice;
import model.bean.Question;
import model.bo.ChoiceBO;
import model.bo.ExamBO;
import model.bo.QuestionBO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/admin/questions")
public class QuestionManager extends HttpServlet {
    private QuestionBO questionBO = new QuestionBO();
    private ExamBO examBO = new ExamBO();
    private ChoiceBO choiceBO = new ChoiceBO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "create":
                request.setAttribute("mode", "create");
                request.setAttribute("listExams", examBO.getAllExams());
                request.getRequestDispatcher("/views/admin/layout.jsp?page=question-form").forward(request, response);
                break;
            case "edit":
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("question", questionBO.getQuestionById(id));
                request.setAttribute("listExams", examBO.getAllExams());
                request.setAttribute("mode", "edit");
                request.getRequestDispatcher("/views/admin/layout.jsp?page=question-form").forward(request, response);
                break;
            case "delete":
                int delId = Integer.parseInt(request.getParameter("id"));
                // Xóa đáp án trước
                choiceBO.deleteChoicesByQuestionId(delId);
                // Xóa câu hỏi sau
                questionBO.deleteQuestion(delId);
                response.sendRedirect(request.getContextPath() + "/admin/questions");
                break;
            default:
                // Xử lý filter tìm kiếm
                String keyword = request.getParameter("keyword");
                String examIdStr = request.getParameter("examId");
                String type = request.getParameter("type");
                int examId = (examIdStr != null && !examIdStr.isEmpty()) ? Integer.parseInt(examIdStr) : 0;

                request.setAttribute("questionList", questionBO.getAdminQuestionsWithDetails(keyword, examId, type));
                request.setAttribute("examList", examBO.getAllExams()); // Để hiển thị dropdown filter
                request.getRequestDispatcher("/views/admin/layout.jsp?page=questions").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        
        int examId = Integer.parseInt(request.getParameter("examId"));
        String content = request.getParameter("content");
        String type = request.getParameter("type");
        
        int questionId = 0;
        
        if ("create".equals(action)) {
            Question q = new Question(0, examId, content, type);
            questionId = questionBO.createQuestion(q);
        } else if ("update".equals(action)) {
            questionId = Integer.parseInt(request.getParameter("id"));
            Question q = new Question(questionId, examId, content, type);
            questionBO.updateQuestion(q);
            // Xóa đáp án cũ để lưu lại đáp án mới (cách đơn giản nhất)
            choiceBO.deleteChoicesByQuestionId(questionId);
        }
        
        // Xử lý lưu đáp án (Choices)
        List<Choice> choices = extractChoices(request);
        choiceBO.saveChoicesForQuestion(questionId, choices);
        
        response.sendRedirect(request.getContextPath() + "/admin/exams?action=edit&id=" + examId);
    }

    // Helper: Lấy danh sách đáp án từ form động
    private List<Choice> extractChoices(HttpServletRequest request) {
        List<Choice> list = new ArrayList<>();
        String[] contents = request.getParameterValues("choiceContent");
        String[] correctIndices = request.getParameterValues("choiceCorrect"); // Trả về index: 0, 1, 3...

        if (contents != null) {
            Set<String> correctSet = new HashSet<>();
            if (correctIndices != null) Collections.addAll(correctSet, correctIndices);

            for (int i = 0; i < contents.length; i++) {
                if (contents[i] != null && !contents[i].trim().isEmpty()) {
                    Choice c = new Choice();
                    c.setContent(contents[i].trim());
                    // Kiểm tra xem index 'i' này có nằm trong danh sách được tick đúng không
                    c.setCorrect(correctSet.contains(String.valueOf(i)));
                    list.add(c);
                }
            }
        }
        return list;
    }
}