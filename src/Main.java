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

        boolean errorOccured = false;
        if(isCorrectWorkFileFormat(workFlowFile)) {
            System.out.println("Correct WorkFlowFile Format");
        }else System.out.println("Wrong WorkFlowFile Format ");


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



    }


    //task da falan yanlış format varsa sonraki yanlışları göstermiyor o düzeltilcek

    public static boolean isCorrectWorkFileFormat(File workFlowFile){    //tüm line ın sayı harf _ . dan oluşmasını parantezlerin ve title ların yerlerini kontrol ediyor

           // ArrayList<String>jobInfoInText=new ArrayList<String>();
           // ArrayList<String>stationInfoInText=new ArrayList<String>();

            boolean taskTypesFound = false;
            boolean jobTypesFound = false;
            boolean stationsFound = false;
            Scanner workScanner = null;

            try {
                workScanner = new Scanner(workFlowFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }


            while (workScanner.hasNextLine()) {
                String line = workScanner.nextLine();
                System.out.println("Line: " + line);
                //BET kaçıncı line da hata verdiğini bul

                if (line.startsWith("(TASKTYPES")) {
                    String[] pieces=line.split(" ");
                    parseTaskTypes(pieces);
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
                        parseJobTypes(pieces);
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
                    System.out.println("girdi");
                    System.out.println("station içi line: "+line);
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

    public static void parseTaskTypes(String[] pieces) {
        Validator validator = new Validator();
        ArrayList<String>realPieces=new ArrayList<String>();
        ArrayList<Task>taskTypesInText=new ArrayList<Task>();

        for(String s: pieces){
            if (!s.contains("(") && !s.contains(")") && !s.contains("(TASKTYPES")) {
                realPieces.add(s);
            }
        }

        for(String s: realPieces){
            System.out.println("pieces: "+s);
        }



        if (validator.isNumber(realPieces.get(0))) {
            System.out.println("Error: task line sayıyla başlıyor");
            //BET error verince sistemi kapat
        } else {
             for (int i = 0; i < realPieces.size(); i++) {

                if (!validator.isNumber(realPieces.get(i))) {
                    if (validator.isValidID(realPieces.get(i))) {
                        System.out.println("task typeID: " + realPieces.get(i));
                        Task task = new Task();
                        task.setTaskType(realPieces.get(i));
                        taskTypesInText.add(task);
                        if((i+1<realPieces.size()) && (validator.isNumber(realPieces.get(i+1)))){    //i+1 düzeltilcek ileride parantez şeyini ayarlayınca hata vercek
                            System.out.println("size: "+realPieces.get(i+1));
                            task.setTaskSize(Double.parseDouble(realPieces.get(i+1)));
                        }

                    } else {
                        System.out.println("not valid id");
                    }

                }
            }
        }
    }

    public static void parseJobTypes(String[] pieces){
        ArrayList<String>realPieces=new ArrayList<String>();
        for(String s: pieces){
            if (!s.contains("(") && !s.contains(")")) {
                realPieces.add(s);
            }
        }

        for(String s: realPieces){
            System.out.println("pieces: "+s);
        }

    }








}

