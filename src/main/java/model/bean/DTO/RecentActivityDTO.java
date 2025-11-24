package model.bean.DTO;

public class RecentActivityDTO {
    private String content;  // Ví dụ: "Nguyễn Văn A vừa nộp bài thi Java"
    private String timeAgo;  // Ví dụ: "5 phút trước"
    private String type;     // 'submission', 'user', 'exam' (để chọn icon)

    public RecentActivityDTO(String content, String timeAgo, String type) {
        this.content = content;
        this.timeAgo = timeAgo;
        this.type = type;
    }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getTimeAgo() { return timeAgo; }
    public void setTimeAgo(String timeAgo) { this.timeAgo = timeAgo; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}