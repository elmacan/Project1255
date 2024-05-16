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
        ArrayList<Job> jobTypesInText = new ArrayList<Job>();
        ArrayList<Task> taskTypesInText = new ArrayList<Task>();
        fileControl(workFileName, jobFileName, taskTypesInText, jobTypesInText);


        for (int i = 0; i < jobTypesInText.size(); i++) {
            jobTypesInText.get(i).printTasks();
        }
       // jobTypesInText.get(0).printTasks();

        int currentTime = 0;

        //event queue yapılcak


    }

    public static void fileControl(String workFileName, String jobFileName,ArrayList<Task> taskTypesInText,ArrayList<Job> jobTypesInText) {
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

    public static boolean isCorrectWorkFileFormat(File workFlowFile,ArrayList<Task> taskTypesInText,ArrayList<Job> jobTypesInText){    //tüm line ın sayı harf _ . dan oluşmasını parantezlerin ve title ların yerlerini kontrol ediyor

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

            String line;

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
                        parseJobTypes(pieces,jobTypesInText,taskTypesInText);
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

    public static void parseJobTypes(String[] pieces,ArrayList<Job> jobTypesInText,ArrayList<Task> taskTypesInText) {
        ArrayList<String> realPieces = new ArrayList<String>();
        Validator validator = new Validator();
        int countIndex = 0;
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
                if ((i + 1 < realPieces.size()) && (validator.isNumber(realPieces.get(i + 1)))) { //t1 den sonra sayı var mı koşulu
                    for (j = 0; j < taskTypesInText.size(); j++) {
                        if (realPieces.get(i).equals(taskTypesInText.get(j).getTaskType())) {
                            System.out.println("------------------deneme:      " + taskTypesInText.get(j).getTaskType());
                            taskTypesInText.get(j).setTaskSize(Double.parseDouble(realPieces.get(i + 1)));
                            jobTypesInText.get(0).getTasks().add(taskTypesInText.get(j));

                        }
                    }
                } else {
                    //j1
                    for (int k = 0; k < taskTypesInText.size(); k++) {
                        if (realPieces.get(i).equals(taskTypesInText.get(k).getTaskType())) {
                            jobTypesInText.get(countIndex).getTasks().add(taskTypesInText.get(k));
                        }
                        //her j için olan task disizi
                    }
                }
            }

        }
        countIndex++;

    }






   /* private static Task findTask(String taskType, ArrayList<Task> taskTypesInText) {
        for (Task task : taskTypesInText) {
            if (task.getTaskType().equals(taskType)) {
                return task;
            }
        }
        return null;
    }
      for (int i = 1; i < pieces.length; i++) {
            String piece = pieces[i];
            Task task = findTask(piece, taskTypesInText);

            if (task != null) {
                jobTypesInText.get(i).getTasks().add(task);
            } else {
                System.out.println("Error: Task not found for job type.");
                return;
            }


        }*/

    //BET stringbuilder bak bir
    //BET parantez öncesi boşluk olmazsa son elemanı almıyor onu sonra düzelt
    public static void parseTaskTypes(String[] pieces,ArrayList<Task> taskTypesInText) {
        Validator validator = new Validator();
        ArrayList<String>realPieces=new ArrayList<String>();


        for(String s: pieces){
            if (!s.contains("(") && !s.contains(")") && !s.contains("(TASKTYPES")) {
                realPieces.add(s);
            }
        }

        for(String s: realPieces){
           // System.out.println("pieces: "+s);
        }

        if (validator.isNumber(realPieces.get(0))) {
            System.out.println("Error: task line sayıyla başlıyor");
            //BET error verince sistemi kapat
        } else {
             for (int i = 0; i < realPieces.size(); i++) {

                if (!validator.isNumber(realPieces.get(i))) {
                    if (validator.isValidID(realPieces.get(i))) {
                        //System.out.println("task typeID: " + realPieces.get(i));
                        Task task = new Task();
                        task.setTaskType(realPieces.get(i));
                        taskTypesInText.add(task);
                        if((i+1<realPieces.size()) && (validator.isNumber(realPieces.get(i+1)))){


                           // System.out.println("size: "+realPieces.get(i+1));
                            task.setTaskSize(Double.parseDouble(realPieces.get(i+1)));
                        }

                    } else {
                        System.out.println("not valid id");
                    }

                }
            }
        }
    }









}

