import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileParser {
    private static ArrayList<String> taskTypesInText = new ArrayList<>();
    private static ArrayList<JobType> jobTypesInText = new ArrayList<>();
    private static ArrayList<Station> stationsInText = new ArrayList<>();
    private static ArrayList<Job> jobsInText = new ArrayList<>();
    private static HashMap<String, String> taskInfo = new HashMap<String, String>();
    //private ArrayList<String> types_jt = new ArrayList<>();
    //private ArrayList<String>sizes_jt=new ArrayList<>();

    public static ArrayList<String> getTaskTypesInText() {
        return taskTypesInText;
    }

    public static void setTaskTypesInText(ArrayList<String> taskTypesInText) {
        FileParser.taskTypesInText = taskTypesInText;
    }

    public static ArrayList<JobType> getJobTypesInText() {
        return jobTypesInText;
    }

    public static void setJobTypesInText(ArrayList<JobType> jobTypesInText) {
        FileParser.jobTypesInText = jobTypesInText;
    }

    public static ArrayList<Station> getStationsInText() {
        return stationsInText;
    }

    public static void setStationsInText(ArrayList<Station> stationsInText) {
        FileParser.stationsInText = stationsInText;
    }

    public static String[] splitStringBySpacesWithoutParentheses(String str) {
        if (str == null || str.isEmpty()) {
            return new String[0];  // Return an empty array if the input string is null or empty
        }
        str = str.replaceAll("[()]", "");
        return str.split("\\s+");  // Split the string by one or more spaces
    }

    public void parseTaskTypes(String[] pieces, String line) {
        Validator validator = new Validator();

        pieces = splitStringBySpacesWithoutParentheses(line);

        for (String s : pieces) {

            if (validator.iscontainsClosingParenthesis(s)) {
                char letterToRemove = ')';
                String modifiedString = s.replace(String.valueOf(letterToRemove), "");
                s = modifiedString;

            }
            if (!s.contains("(") && !s.contains(")") && !s.contains("(TASKTYPES")) {
                taskTypesInText.add(s);
            }

        }


        for (int i = 1; i < taskTypesInText.size(); i++) {

            if (!validator.isNumber(taskTypesInText.get(i))) {

                if (i + 1 < taskTypesInText.size() && validator.isNumber(taskTypesInText.get(i + 1))) {

                    taskInfo.put(taskTypesInText.get(i), taskTypesInText.get(i + 1));
                } else {
                    taskInfo.put(taskTypesInText.get(i), " ");
                }
                if (!validator.isValidID(taskTypesInText.get(i))) {
                    validator.addError(i + " is an invalid taskTypeID");

                }
                if (!validator.isUniqueID(taskTypesInText.get(i))) {
                    validator.addError(taskTypesInText.get(i) + "is listed more than once");

                }


            }


            if (validator.isNumber(taskTypesInText.get(i))) {
                if (validator.isNegativeSize(taskTypesInText.get(i))) {
                    validator.addError(taskTypesInText.get(i - 1) + " has a negative size: " + taskTypesInText.get(i));
                }
            }

        }

        if (validator.isNumber(taskTypesInText.get(0))) {
            validator.addError("Line 1 : TaskTypes information starts with a number");

        }

        for (String key : taskInfo.keySet()) {
            System.out.println("task: " + key + " size: " + taskInfo.get(key));
        }

    }

    public double findDefaultSizeOfTaskType(String taskTypeID, ArrayList<String> taskTypesInText) {
        Validator validator = new Validator();

        for (int i = 0; i < taskTypesInText.size(); i++) {

            if (taskTypeID.equals(taskTypesInText.get(i))) {

                if (i + 1 < taskTypesInText.size() && validator.isNumber(taskTypesInText.get(i + 1))) {


                    return Double.parseDouble(taskTypesInText.get(i + 1));
                }
            }
        }
        return -1;
    }

    //boşluklara ve parantezlere en son kontrol et error için
    public void parseJobTypes(String[] pieces, int countIndex, int lineCounter) {
        ArrayList<String> realPieces = new ArrayList<String>();
        Validator validator = new Validator();

        for (String s : pieces) {
            if (!s.contains("(") && !s.contains(")")) {
                realPieces.add(s);
            }
        }

        for (String s : realPieces) {
            System.out.println(s);
        }

        JobType jobtype = new JobType();
        if (validator.isValidID(realPieces.get(0))) {
            jobtype.setJobTypeID(realPieces.get(0));
            jobTypesInText.add(jobtype);
        } else {
            validator.addError("Line: " + lineCounter + ": " + realPieces.get(0) + " is invalid JobTypeID");

        }

        int j = 0;

        if (validator.isNumber(realPieces.get(0)) || validator.isNumber(realPieces.get(1))) {
            validator.addError("Line " + lineCounter + ": taskTypeID not found at startup");
        }

        for (int i = 1; i < realPieces.size(); i++) {
            if (!validator.isNumber(realPieces.get(i))) {
                if (validator.isValidID(realPieces.get(i))) {
                    Task task = new Task();
                    task.setTaskType(realPieces.get(i));
                    jobtype.getTasks().add(task);

                    if ((i + 1 < realPieces.size()) && (validator.isNumber(realPieces.get(i + 1)))) {

                        task.setTaskSize(Double.parseDouble(realPieces.get(i + 1)));
                        //job.getTasks().add(task);
                    } else {

                        if (findDefaultSizeOfTaskType(realPieces.get(i), taskTypesInText) == -1) {
                            validator.addError("Line " + lineCounter + ": " + realPieces.get(i) + " has no default size, either a default size must be declared in TASKTYPE" +
                                    "list or the size must be declared within the job");

                        } else {

                            task.setTaskSize(findDefaultSizeOfTaskType(realPieces.get(i), taskTypesInText));
                            //job.getTasks().add(task);
                        }
                    }

                } else {
                    validator.addError("Line " + lineCounter + ": " + realPieces.get(i) + " invalid taskTypeID");
                }

            }

        }
        System.out.println(jobtype.toString());


    }

    public void parseStations(String line, int lineCounter) {
        String[] pieces = splitStringBySpacesWithoutParentheses(line);
        Validator validator = new Validator();

        for (String s : pieces) {
            System.out.println(s);

        }

        if (validator.isValidID(pieces[0])) {
            String stationId = pieces[0];
        } else {
            validator.addError("Line " + lineCounter + ": " + pieces[0] + " invalid StationID");
        }

        int maxCapacity = Integer.parseInt(pieces[1]);
        boolean multiFlag = pieces[2].equals("Y");
        boolean fifoFlag = pieces[3].equals("Y");
        HashMap<String, Double> speeds = new HashMap<>();

        Station station = new Station();

        for (int i = 4; i < pieces.length; i++) {
            if (!validator.isNumber(pieces[i])) {

            }

        }


    }

    public void parseJobFile(File jobFile) {
        Scanner jobScanner = null;
        Validator validator = new Validator();

        try {
            jobScanner = new Scanner(jobFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String line;

        while (jobScanner.hasNextLine()) {
            line = jobScanner.nextLine();

            System.out.println("Line: " + line);
            String[] parts = line.split("\\s+");
            if (parts.length == 4) {
                String jobId = parts[0];
                String jobTypeId = parts[1];
                int startTime = Integer.parseInt(parts[2]);
                int duration = Integer.parseInt(parts[3]);
                if (findJobTypeForJobFile(jobTypeId) == null) {
                    validator.addError("Line: " + line + "|| There is no such jobType: " + jobTypeId);

                } else {
                    Job job = new Job(jobId, findJobTypeForJobFile(jobTypeId), startTime, duration);
                    jobsInText.add(job);
                }


            } else {
                validator.addError("Error in JobFiles: Incorrect job format at line  || " + line);
            }


        }
    }

    public JobType findJobTypeForJobFile(String jobTypeId) {
        for (int i = 0; i < jobTypesInText.size(); i++) {
            if (jobTypeId.equals(jobTypesInText.get(i).getJobTypeID())) {
                return jobTypesInText.get(i);
            }

        }
        return null;


    }

    public void printFileInfo() {
        System.out.println();
        System.out.println("---------------JobFile INFO-------------");
        for (int i = 0; i < jobsInText.size(); i++) {
            System.out.println(jobsInText.get(i).toString());
            System.out.println();
        }

    }


}
