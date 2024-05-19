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

        //fileParser.printFile();
        // Create FileParser and ReportGenerator instances

       /* FileParser fileParser2 = new FileParser();
        ReportGenerator reportGenerator = new ReportGenerator();

        // Parse job file and get completed and overdue jobs
        List<Job> completedJobs = fileParser2.parseJobFile(jobFileName);
        List<Job> overdueJobs = fileParser2.getOverdueJobs(); // Ensure this method is implemented in FileParser

        // Generate and print simulation report
        reportGenerator.generateReport(completedJobs, overdueJobs);*/


    }


}

