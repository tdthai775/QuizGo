package model.bean;

public class DashboardStats {
    private int totalExams;
    private int totalQuestions;
    private int totalUsers;
    private int totalSubmissions;
    private int pendingJobs;

    public DashboardStats() {}

    public DashboardStats(int totalExams, int totalQuestions, int totalUsers,
                          int totalSubmissions, int pendingJobs) {
        this.totalExams = totalExams;
        this.totalQuestions = totalQuestions;
        this.totalUsers = totalUsers;
        this.totalSubmissions = totalSubmissions;
        this.pendingJobs = pendingJobs;
    }

    public int getTotalExams() { return totalExams; }
    public void setTotalExams(int totalExams) { this.totalExams = totalExams; }

    public int getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(int totalQuestions) { this.totalQuestions = totalQuestions; }

    public int getTotalUsers() { return totalUsers; }
    public void setTotalUsers(int totalUsers) { this.totalUsers = totalUsers; }

    public int getTotalSubmissions() { return totalSubmissions; }
    public void setTotalSubmissions(int totalSubmissions) { this.totalSubmissions = totalSubmissions; }

    public int getPendingJobs() { return pendingJobs; }
    public void setPendingJobs(int pendingJobs) { this.pendingJobs = pendingJobs; }
}
