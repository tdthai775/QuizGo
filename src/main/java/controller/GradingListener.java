package controller;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class GradingListener implements ServletContextListener{
	private GradingWorker worker;
	public void contextInitialized(ServletContextEvent sce) {
        worker = new GradingWorker();
        worker.start();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        if (worker != null) worker.stop();
    }
}
