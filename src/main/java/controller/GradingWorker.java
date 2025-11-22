package controller;
import model.bean.*;
import model.bo.*;

public class GradingWorker extends Thread {
	private SubmissionBO submissionBO = new SubmissionBO();
	
	@Override
    public void run() {
        while (true) {
            try {
                Job job = submissionBO.getPendingJob(); 
                
                if (job != null) {
                    System.out.println("Đang chấm bài ID: " + job.getSubmissionId());
                    
                    Thread.sleep(5000); 
                    
                    submissionBO.gradeSubmission(job.getSubmissionId());
                    
                    submissionBO.updateJobStatus(job.getId(), "DONE");
                } else {
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
