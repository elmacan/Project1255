import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static ArrayList<String> usedIDs = new ArrayList<>();
    private FileParser fileParser= new FileParser();
    private static List<String> errorCollector = new ArrayList<>();



    public void addError(String error) {
        errorCollector.add(error);
    }

    public boolean hasErrors() {
        return !errorCollector.isEmpty();
    }

    public List<String> getErrorCollector() {
        return errorCollector;
    }

    public boolean isNumber(String str) {
        // Regular expression to match numbers (integer or decimal)     //burda negatif olmasına göre exception atabiliyor muyuz????????????????
        String regex = "\\-?[0-9]*[.]?[0-9]*";   //doğru regex

        // Compile the regular expression
        Pattern pattern = Pattern.compile(regex);

        // Create matcher object
        Matcher matcher = pattern.matcher(str);

        // Check if the string matches the condition
        return matcher.matches();
    }


    public boolean isValidID(String id){
        String regex = "^[a-zA-Z][a-zA-Z0-9_]*$"; //doğru regex

        // Compile the regular expression
        Pattern pattern = Pattern.compile(regex);

        // Create matcher object
        Matcher matcher = pattern.matcher(id);

        // Check if the string matches the condition

        return matcher.matches();
    }



    public boolean isUniqueID(String id){
        // Check if the ID is already used
        if (usedIDs.contains(id)) {
            return false; // ID is not unique
        }

        // Add the ID to the list of used IDs
        usedIDs.add(id);
        return true; // ID is unique

    }

    public boolean isNegativeSize(String size){
        if(Double.parseDouble(size)<0 ){
            return true;
        }
        return false;

    }

    public  boolean iscontainsClosingParenthesis(String str) {

        return str.indexOf(')') != -1;
    }
    public void fileControl(String workFileName, String jobFileName) {
        File workFlowFile = new File(workFileName);
        File jobFile = new File(jobFileName);

        boolean errorOccured = false;


        try {
            if (!workFlowFile.exists() || !workFlowFile.canRead()) {

                throw new IOException(workFileName + " doesn't exists or is not readable!");

            }
        } catch (IOException e) {
            errorOccured=true;
            System.out.println(e.getMessage());
            return;




        }
        try {
            if (!jobFile.exists() || !jobFile.canRead()) {

                throw new IOException(jobFileName + " doesn't exists or is not readable!");
            }
        } catch (IOException e) {
            errorOccured=true;
            System.out.println(e.getMessage());
            return;



        }

        if(isCorrectWorkFileFormat(workFlowFile)&&!this.hasErrors()) {
            System.out.println("---------------------------");
            System.out.println("Correct WorkFlowFile Format");

        }

        fileParser.parseJobFile(jobFile);




        if(errorOccured){
            System.exit(1);
        }



    }

    public  boolean isCorrectWorkFileFormat(File workFlowFile){
        int lineCounter=0;

        int countIndex=0;
        boolean taskTypesFound = false;
        boolean jobTypesFound = false;
        boolean stationsFound = false;
        Scanner workScanner = null;

        try {
            workScanner = new Scanner(workFlowFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String line;
        //workfile dosyasını okuyan scanner objesi
        while (workScanner.hasNextLine()) {
            line = workScanner.nextLine();
            lineCounter++;
            System.out.println();
            System.out.println("Line: " + line);

            if (line.startsWith("(TASKTYPES ")) {
                String[] pieces=line.split(" ");
                fileParser.parseTaskTypes(pieces,line);
                String splittedline=line.replaceAll("\\s", "");//tüm boşlukları çıkarıyor
                taskTypesFound = true;
                if (!splittedline.matches("^\\(TASKTYPES(\\w[.]?)*\\)$")) {
                    errorCollector.add("Line 1: Invalid format in TASKTYPES section like an error having unwanted characters or does not having the correct number of parentheses");

                    return false;
                }
                continue;
            } else if (line.equals("(JOBTYPES")) {

                while(!(line.startsWith("(STATIONS"))) {

                    line = workScanner.nextLine();
                    lineCounter++;
                    String[] pieces=line.split(" ");
                    fileParser.parseJobTypes(pieces,countIndex,lineCounter);
                    countIndex++;
                    System.out.println("line: "+line);
                    String splittedline = line.replaceAll("\\s", "");//boşlukları çıkarıyor
                    if(splittedline.matches("^\\((\\w[.]?)*\\)\\)$"))break;
                    jobTypesFound = true;
                    if ( !((splittedline.matches("^\\((\\w[.]?)*\\)$")) || (splittedline.matches("^\\((\\w[.]?)*\\)\\)$")) )) {
                        errorCollector.add("Line "+lineCounter+" : Invalid format in JOBTYPES section like an error having unwanted characters or does not having the correct number of parentheses");

                        return false;
                    }

                }
                continue;
            } else if (line.equals("(STATIONS")) {
                System.out.println();
                while(workScanner.hasNextLine()){
                    line = workScanner.nextLine();

                    lineCounter++;
                    System.out.println("line: " + line);
                    stationsFound = true;
                    fileParser.parseStations(line,lineCounter);
                    String splittedline = line.replaceAll("\\s", "");//boşlukları çıkarıyor

                    if (!((splittedline.matches("^\\((\\w[.]?)*\\)$")) || (splittedline.matches("^\\((\\w[.]?)*\\)\\)$")) )) {
                        errorCollector.add("Line " + lineCounter + ": Invalid format in STATIONS section like an error having unwanted characters or does not having the correct number of parentheses");

                        return false;
                    }
                }

            }

            if (!taskTypesFound) {
                errorCollector.add(line+"\nTASKTYPES section not found. Line should start like (TASKTYPES ");
                return false;
            }
            if (!jobTypesFound) {
                errorCollector.add("Error: JOBTYPES section not found. Line should start like (JOBTYPES");
                return false;
            }
            if (!stationsFound) {
                errorCollector.add("Error: STATIONS section not found. Line should start like (STATIONS");
                return false;
            }


        }
        return true;
    }

   /* public boolean isCorrectJobFileFormat(File jobFile){

        fileParser.parseJobFile();

        return true;
    }*/


}
