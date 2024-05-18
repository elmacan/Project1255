import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileParser {
    private static ArrayList<String> taskTypesInText = new ArrayList<>();
    private static ArrayList<Job> jobTypesInText = new ArrayList<>();
    private static ArrayList<Station> stationsInText = new ArrayList<>();


    public void parseTaskTypes(String[] pieces) {
        Validator validator = new Validator();




        for(String s: pieces){
            if (!s.contains("(") && !s.contains(")") && !s.contains("(TASKTYPES")) {
                taskTypesInText.add(s);
            }
        }

        for(String s: taskTypesInText){
            if(!validator.isValidID(s)){
                System.out.println(s+" invalid TaskType");

            }

        }

        if (validator.isNumber(taskTypesInText.get(0))) {
            System.out.println("Error: TaskTypes start with a number in Line 1");
            System.exit(1);
        }



    }


    public  double findDefaultSizeOfTaskType(String taskTypeID, ArrayList<String> taskTypesInText) {
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



    public  void parseJobTypes(String[] pieces,int countIndex) {
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
                if (validator.isValidID(realPieces.get(i))) {
                    Task task = new Task();
                    task.setTaskType(realPieces.get(i));
                    job.getTasks().add(task);

                    if ((i + 1 < realPieces.size()) && (validator.isNumber(realPieces.get(i + 1)))) {

                        task.setTaskSize(Double.parseDouble(realPieces.get(i + 1)));
                        //job.getTasks().add(task);
                    } else {

                        if (findDefaultSizeOfTaskType(realPieces.get(i), taskTypesInText) == -1) {
                            System.out.println(realPieces.get(i) + " has no size");

                        } else {

                            task.setTaskSize(findDefaultSizeOfTaskType(realPieces.get(i), taskTypesInText));
                            //job.getTasks().add(task);
                        }
                    }

                }
            }

        }


    }


    public void printFile(){
        for(int i=0;i<jobTypesInText.size();i++){
            jobTypesInText.get(i).printTasks();
        }

    }


    public static ArrayList<String> getTaskTypesInText() {
        return taskTypesInText;
    }

    public static void setTaskTypesInText(ArrayList<String> taskTypesInText) {
        FileParser.taskTypesInText = taskTypesInText;
    }

    public static ArrayList<Job> getJobTypesInText() {
        return jobTypesInText;
    }

    public static void setJobTypesInText(ArrayList<Job> jobTypesInText) {
        FileParser.jobTypesInText = jobTypesInText;
    }

    public static ArrayList<Station> getStationsInText() {
        return stationsInText;
    }

    public static void setStationsInText(ArrayList<Station> stationsInText) {
        FileParser.stationsInText = stationsInText;
    }
















}
