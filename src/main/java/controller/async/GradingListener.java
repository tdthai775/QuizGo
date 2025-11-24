package controller.async;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class GradingListener implements ServletContextListener {
    private GradingWorker worker;

    // Khi Server khởi động
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(">>> Quiz System: Starting Grading Worker...");
        worker = new GradingWorker();
        worker.start();
    }

    // Khi Server tắt
    public void contextDestroyed(ServletContextEvent sce) {
        if (worker != null) {
            System.out.println(">>> Quiz System: Stopping Grading Worker...");
            worker.stopWorker();
        }
    }
}