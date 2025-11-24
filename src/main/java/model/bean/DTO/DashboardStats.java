package model.bean.DTO;

public class DashboardStats {
    private int totalExams;
    private int totalUsers;
    private int totalSubmissions;

    public DashboardStats() {}

    public DashboardStats(int totalExams, int totalQuestions, int totalUsers, int totalSubmissions) {
        this.totalExams = totalExams;
        this.totalUsers = totalUsers;
        this.totalSubmissions = totalSubmissions;
    }

    public int getTotalExams() { return totalExams; }
    public void setTotalExams(int totalExams) { this.totalExams = totalExams; }

    public int getTotalUsers() { return totalUsers; }
    public void setTotalUsers(int totalUsers) { this.totalUsers = totalUsers; }

    public int getTotalSubmissions() { return totalSubmissions; }
    public void setTotalSubmissions(int totalSubmissions) { this.totalSubmissions = totalSubmissions; }
}