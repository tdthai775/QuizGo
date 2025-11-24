package model.bo;

import java.util.List;

import model.bean.DTO.QuestionResultDTO;
import model.bean.DTO.ResultDTO;
import model.dao.ResultDAO;

public class ResultBO {
private ResultDAO resultDAO = new ResultDAO();
    
    // Tận dụng SubmissionBO cũ để lấy chi tiết câu hỏi/đáp án
    private SubmissionBO submissionBO = new SubmissionBO();

    // Tìm kiếm kết quả
    public List<ResultDTO> searchResults(String keyword, String examId, String date) {
        return resultDAO.searchResults(keyword, examId, date);
    }

    // SỬA: Gọi DAO để lấy dữ liệu thật, không trả về null nữa
    public ResultDTO getResultById(int id) {
        return resultDAO.getResultById(id);
    }
    
    // Lấy chi tiết từng câu hỏi (reuse logic của User)
    public List<QuestionResultDTO> getResultDetails(int submissionId) {
        return submissionBO.getResultDetail(submissionId);
    }
}