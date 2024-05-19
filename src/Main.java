import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Error: Please provide exactly two file names.");
            return;
        }
        String workFileName = args[0];
        String jobFileName = args[1];

        Validator validator=new Validator();
        validator.fileControl(workFileName,jobFileName);

        FileParser fileParser = new FileParser();
        fileParser.printFileInfo();






        // Checking for errors
        if (validator.hasErrors()) {
            System.out.println("----------------------");
            System.out.println("Errors occurred:");
            System.out.println("----------------------");
            for (String error : validator.getErrorCollector()) {
                System.out.println(error);
            }
            System.exit(1);
        } else {
            System.out.println("---------------------");
            System.out.println("No errors detected.");
            System.out.println("---------------------");
        }






    }
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

