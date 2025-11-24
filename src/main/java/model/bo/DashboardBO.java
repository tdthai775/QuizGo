package model.bo;

import java.util.List;

import model.bean.DTO.DashboardStats;
import model.bean.DTO.PopularExamDTO;
import model.bean.DTO.RecentActivityDTO;
import model.dao.DashboardDAO;
public class DashboardBO {
    private DashboardDAO dashboardDAO = new DashboardDAO();

    public DashboardStats getDashboardStats() {
        return dashboardDAO.getStats();
    }

    public List<RecentActivityDTO> getRecentActivities(int limit) {
        return dashboardDAO.getRecentActivities();
    }

    public List<PopularExamDTO> getPopularExams(int limit) {
        return dashboardDAO.getPopularExams();
    }
}