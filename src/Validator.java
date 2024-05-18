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
    private List<String> errorCollector = new ArrayList<>();



    public void addError(String error) {
        errorCollector.add(error);
    }

    public boolean hasErrors() {
        return !errorCollector.isEmpty();
    }

    public List<String> getErrors() {
        return errorCollector;
    }

    public boolean isNumber(String str) {
        // Regular expression to match numbers (integer or decimal)     //burda negatif olmasına göre exception atabiliyor muyuz????????????????
        String regex = "[0-9]*[.]?[0-9]*";   //doğru regex

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

        if(isCorrectWorkFileFormat(workFlowFile)) {
            System.out.println("Correct WorkFlowFile Format");

        }else {
            System.out.println("Wrong WorkFlowFile Format ");
            System.exit(1);
        }



        if(errorOccured){
            System.exit(1);
        }



    }

    public  boolean isCorrectWorkFileFormat(File workFlowFile){

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
            System.out.println("Line: " + line);
            //BET kaçıncı line da hata verdiğini bul

            if (line.startsWith("(TASKTYPES")) {
                String[] pieces=line.split(" ");
                fileParser.parseTaskTypes(pieces);
                String splittedline=line.replaceAll("\\s", "");//boşlukları çıkarıyor
                taskTypesFound = true;
                if (!splittedline.matches("^\\(TASKTYPES(\\w[.]?)*\\)$")) {
                    System.out.println("Error: Invalid format in TASKTYPES section.");
                    return false;
                }
                continue;
            } else if (line.equals("(JOBTYPES")) {

                while(!(line.startsWith("(STATIONS"))) {
                    line = workScanner.nextLine();
                    String[] pieces=line.split(" ");
                    fileParser.parseJobTypes(pieces,countIndex);
                    countIndex++;
                    System.out.println("line: "+line);
                    String splittedline = line.replaceAll("\\s", "");//boşlukları çıkarıyor
                    if(splittedline.matches("^\\((\\w[.]?)*\\)\\)$"))break;
                    jobTypesFound = true;
                    if ( !((splittedline.matches("^\\((\\w[.]?)*\\)$")) || (splittedline.matches("^\\((\\w[.]?)*\\)\\)$")) )) {
                        System.out.println("Error: Invalid format in JOBTYPES section.");
                        return false;
                    }

                }
                continue;
            } else if (line.equals("(STATIONS")) {
                line=workScanner.nextLine();
                System.out.println("line: "+line);
                stationsFound = true;
                String splittedline = line.replaceAll("\\s", "");//boşlukları çıkarıyor

                if (!splittedline.matches("^\\((\\w[.]?)*\\)$")) {
                    System.out.println("Error: Invalid format in STATIONS section.");
                    return false;
                }
            }

            if (!taskTypesFound) {
                System.out.println("Error: TASKTYPES section not found.");
                return false;
            }
            if (!jobTypesFound) {
                System.out.println("Error: JOBTYPES section not found.");
                return false;
            }
            if (!stationsFound) {
                System.out.println("Error: STATIONS section not found.");
                return false;
            }


        }
        return true;
    }


}
