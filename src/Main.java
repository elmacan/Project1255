import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Error: Please provide exactly two file names.");
            return;
        }
        String workFileName = args[0];
        String jobFileName = args[1];
        fileControl(workFileName, jobFileName);


        int currentTime = 0;

        //event queue yapılcak

    }


    public static void fileControl(String workFileName, String jobFileName) {
        File workFlowFile = new File(workFileName);
        File jobFile = new File(jobFileName);
        Scanner workScanner = null;
        Scanner jobScanner = null;
        boolean errorOccured = false;
        isCorrectWorkFileFormat(workFlowFile);


        ArrayList<Job> jobTypesInText = new ArrayList<Job>();
        ArrayList<Station> stationsInText = new ArrayList<Station>();


        try {
            if (!workFlowFile.exists() || !workFlowFile.canRead()) {
                throw new IOException(workFileName + " doesn't exists or is not readable!");

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            errorOccured = true;

        }
        try {
            if (!jobFile.exists() || !jobFile.canRead()) {
                throw new IOException(jobFileName + " doesn't exists or is not readable!");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            errorOccured = true;

        }

        if (errorOccured == true) {
            System.exit(1);
        }


       /* try {
            workScanner = new Scanner(workFlowFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //sets the delimiter to the end of the file (\\Z), and then reads the entire content of the file into a string.
        String fullWorkFileText=workScanner.useDelimiter("\\Z").next();
        System.out.println(fullWorkFileText);*/

       /* while (workScanner.hasNextLine()) {
            String line = workScanner.nextLine();
            System.out.println("Line: " + line);

            String[] parts = line.split(" ");

            switch (parts[0]) {
                case "(TASKTYPES":
                   Task task=new Task();
                   task.parseTaskTypes(parts);
                    break;
                case "(JOBTYPES":
                    // parseJobTypes(parts);
                    break;
                case "(STATIONS":
                    //parseStations(parts);
                    break;
                default:
                    System.out.println("Syntax error in titles");
            }
        }*/
    }

    public static boolean isCorrectWorkFileFormat(File workFlowFile){
        try {
            Scanner workScanner=new Scanner(workFlowFile);
            //sets the delimiter to the end of the file (\\Z), and then reads the entire content of the file into a string.
            String content = workScanner.useDelimiter("\\Z").next();
            String taskTypesPattern = "TASKTYPES\\s*\\((.*)\\)";
            String jobTypesPattern = "JOBTYPES\\s*\\((.*)\\)";
            String stationsPattern = "STATIONS\\s*\\((.*)\\)";

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return true;
    }


}

