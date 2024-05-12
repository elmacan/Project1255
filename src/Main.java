import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Error: Please provide exactly two file names.");
            return;
        }
        String workFileName = args[0];
        String jobFileName = args[1];
        fileControl(workFileName, jobFileName);

        int currentTime=0;

        //event queue yapılcak

    }


    public static void fileControl(String workFileName, String jobFileName) {
        File workFlowFile = new File(workFileName);
        File jobFile = new File(jobFileName);
        Scanner workScanner = null;
        Scanner jobScanner = null;
        boolean errorOccured = false;


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


        try {
            workScanner = new Scanner(workFlowFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (workScanner.hasNextLine()) {
            String line = workScanner.nextLine();
            System.out.println("Line: " + line);
        }


    }
}



