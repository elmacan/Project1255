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

        int currentTime=0;

        //event queue yapılcak

    }


    public static void fileControl(String workFileName, String jobFileName) {
        File workFlowFile = new File(workFileName);
        File jobFile = new File(jobFileName);
        Scanner workScanner = null;
        Scanner jobScanner = null;
        boolean errorOccured = false;

        ArrayList<Task> taskTypesInText = new ArrayList<Task>();

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
            String[] parts=line.split(" ");

            switch (parts[0]) {
                case "(TASKTYPES":
                    parseTaskTypes(parts);
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
        }
    }







    //stationID strings must start with a letter
    //followed by more letters and/or digits or underscore character ‘_’.

    public static void parseTaskTypes(String[] parts){   //0 tasktype yazan eleman

        for(int i=1;i< parts.length;i++){
            System.out.println("parts i:"+i+"  "+parts[i]);
        }
        int indexCount=1;
        while(!hasClosingParenthesisAtLast(parts[indexCount])){

        }


    }


    public static boolean isValidID(String id){

        String regex = "^[a-zA-Z][a-zA-Z0-9_]*$";

        // Compile the regular expression
        Pattern pattern = Pattern.compile(regex);

        // Create matcher object
        Matcher matcher = pattern.matcher(id);

        // Check if the string matches the condition
        return matcher.matches();


    }
    public static boolean isNumber(String str) {
        // Regular expression to match numbers (integer or decimal)
        String regex = "^-?\\d*\\.?\\d+$";

        // Compile the regular expression
        Pattern pattern = Pattern.compile(regex);

        // Create matcher object
        Matcher matcher = pattern.matcher(str);

        // Check if the string matches the condition
        return matcher.matches();
    }

    public static boolean iscontainsClosingParenthesis(String str) {

        return str.indexOf(')') != -1;
    }
    public static boolean hasClosingParenthesisAtLast(String str) {
        char lastChar = str.charAt(str.length() - 1);
        if(lastChar == ')')return true;

        return false;
    }

    public static boolean isValidTaskTypesLineFormat(String str) {
        // Regular expression to match the format
        String regex = "\\(TASKTYPES ([a-zA-Z]\\w*\\s*(\\.\\d+)?\\s*)+\\)";

        // Compile the regular expression
        Pattern pattern = Pattern.compile(regex);

        // Create matcher object
        Matcher matcher = pattern.matcher(str);

        // Check if the string matches the format
        return matcher.matches();
    }



}



