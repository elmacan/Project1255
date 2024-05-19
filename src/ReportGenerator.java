import java.util.List;
public class ReportGenerator {

    public void generateReport(List<Job> completedJobs, List<Job> overdueJobs) {
        System.out.println("=== Simulation Report ===");
        if (completedJobs == null) {
            System.out.println("No completed jobs."); // Handle the case when completedJobs is null
        } else {
            System.out.println("Completed Jobs:");
            for (Job job : completedJobs) {
                System.out.println("Job ID: " + job.getJobID() + ", Job Type: " + job.getJobType().getJobTypeID() + ", Complete Time: " + job.getCompleteTime());
            }
        }
        // Similar null check for overdueJobs
    }
}


