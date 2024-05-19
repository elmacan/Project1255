import java.util.List;

public class ReportGenerator {

    public void generateReport(List<Job> completedJobs, List<Job> overdueJobs) {
        System.out.println("=== Simulation Report ===");
        System.out.println("Completed Jobs:");
        if (completedJobs != null) {
            for (Job job : completedJobs) {
                System.out.println("Job ID: " + job.getJobID() + ", Job Type: " + job.getJobType().getJobTypeID() + ", Complete Time: " + job.getCompleteTime());
            }
        } else {
            System.out.println("No completed jobs.");
        }

        System.out.println("\nOverdue Jobs:");
        if (overdueJobs != null) {
            for (Job job : overdueJobs) {
                System.out.println("Job ID: " + job.getJobID() + ", Job Type: " + job.getJobType().getJobTypeID() + ", Deadline: " + job.getDeadline());
            }
        } else {
            System.out.println("No overdue jobs.");
        }
    }
}
