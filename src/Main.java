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
        ArrayList<Job> jobTypesInText = new ArrayList<Job>(); //job objelerini içeren arraylist
        ArrayList<String> taskTypesInText = new ArrayList<String>();  //task objelerini içeren list
        fileControl(workFileName, jobFileName, taskTypesInText, jobTypesInText);


        for (int i = 0; i < jobTypesInText.size(); i++) {
            jobTypesInText.get(i).printTasks();
        }
       // jobTypesInText.get(0).printTasks();

        int currentTime = 0;

        //event queue yapılcak


    }

    public static void fileControl(String workFileName, String jobFileName,ArrayList<String> taskTypesInText,ArrayList<Job> jobTypesInText) {
        File workFlowFile = new File(workFileName);
        File jobFile = new File(jobFileName);

        boolean errorOccured = false;
        if(isCorrectWorkFileFormat(workFlowFile,taskTypesInText,jobTypesInText)) {
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


    //BET exception olarak çevir
    //BET description error örneklerine göre bak
    //BET human readable versiyonu ekle
    //BET errorların kaçıncı satırda olduğunu yazdır
    //BET task da falan yanlış format varsa sonraki yanlışları göstermiyor o düzeltilcek

    public static boolean isCorrectWorkFileFormat(File workFlowFile,ArrayList<String> taskTypesInText,ArrayList<Job> jobTypesInText){    //tüm line ın sayı harf _ . dan oluşmasını parantezlerin ve title ların yerlerini kontrol ediyor

           // ArrayList<String>stationInfoInText=new ArrayList<String>();
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
                    parseTaskTypes(pieces,taskTypesInText);
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
                        parseJobTypes(pieces,jobTypesInText,taskTypesInText,countIndex);
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

    public static void parseJobTypes(String[] pieces,ArrayList<Job> jobTypesInText,ArrayList<String> taskTypesInText,int countIndex) {
        ArrayList<String> realPieces = new ArrayList<String>();
        Validator validator = new Validator();

        for (String s : pieces) {
            if (!s.contains("(") && !s.contains(")")) {
                realPieces.add(s);
            }
        }

        for (String s : realPieces) {
            System.out.println("pieces: " + s);
        }

        Job job = new Job();
        if (validator.isValidID(realPieces.get(0))) {
            job.setJobType(realPieces.get(0));
            jobTypesInText.add(job);
        } else {
            System.out.println("invalid jobtypeId");
            System.exit(1);

        }


        int j = 0;
        //BET declare olmamış task var mı
        for (int i = 1; i < realPieces.size(); i++) {
            if (!validator.isNumber(realPieces.get(i))) { //t1 e bakan
                if(validator.isValidID(realPieces.get(i))){
                    Task task=new Task();
                    task.setTaskType(realPieces.get(i));
                    jobTypesInText.get(countIndex).getTasks().add(task);
                    if ((i + 1 < realPieces.size()) && (validator.isNumber(realPieces.get(i + 1)))) {
                        task.setTaskSize(Double.parseDouble(realPieces.get(i+1)));

                    }
                    else{
                        if(findDefaultSizeOfTaskType(realPieces.get(i),taskTypesInText )==-1){
                            System.out.println(realPieces.get(i)+" has no size");
                        }
                        else{
                            task.setTaskSize(findDefaultSizeOfTaskType(realPieces.get(i),taskTypesInText ));
                        }

                    }


                }
            }

        }


    }

    public static double findDefaultSizeOfTaskType(String taskTypeID,ArrayList<String> taskTypesInText){
            Validator validator=new Validator();
            double defeaultSize=-1;
            for(int i=0;i< taskTypesInText.size();i++){
                if(taskTypesInText.get(i).equals(taskTypeID)){
                        if((i + 1 < taskTypesInText.size()) && (validator.isNumber(taskTypesInText.get(i + 1)))){
                            return Double.parseDouble(taskTypesInText.get(i+1));
                    }

                }
            }


       return defeaultSize;
    }


    //BET stringbuilder bak bir
    //BET parantez öncesi boşluk olmazsa son elemanı almıyor onu sonra düzelt
    public static void parseTaskTypes(String[] pieces,ArrayList<String> taskTypesInText) {
        Validator validator = new Validator();




        for(String s: pieces){
            if (!s.contains("(") && !s.contains(")") && !s.contains("(TASKTYPES")) {
                taskTypesInText.add(s);
            }
        }

        for(String s: taskTypesInText){
           // System.out.println("pieces: "+s);
        }

        if (validator.isNumber(taskTypesInText.get(0))) {
            System.out.println("Error: task line sayıyla başlıyor");
            //BET error verince sistemi kapat
        }


    }











}

