import java.util.List;
public class ReportGenerator {

    public void generateReport(List<Job> completedJobs, List<Job> overdueJobs) {
        System.out.println("=== Simulation Report ===");
        System.out.println("Completed Jobs:");
        for (Job job : completedJobs) {
            System.out.println("Job ID: " + job.getJobID() + ", Job Type: " + job.getJobType().getJobTypeID() + ", Complete Time: " + job.getCompleteTime());
        }
        System.out.println("\nOverdue Jobs:");
        for (Job job : overdueJobs) {
            System.out.println("Job ID: " + job.getJobID() + ", Job Type: " + job.getJobType().getJobTypeID() + ", Deadline: " + job.getDeadline());
        }
    }
}

