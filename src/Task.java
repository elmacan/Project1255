import java.util.ArrayList;
public class Task {
    private String taskType;
    private double taskSize;
    private double duration;   // =task size/station speed for that task
    private String stationID; //hangi stationda execute olacağı
    private String status;  //wait,execute,complete


    public Task(){

    }
    public Task(String taskType, double taskSize, double duration, String stationID, String status) {
        this.taskType = taskType;
        this.taskSize = taskSize;
        this.duration = duration;
        this.stationID = stationID;
        this.status = status;
    }




    //kullanılabilir sample code
    /* while (workScanner.hasNextLine()) {
            String line = workScanner.nextLine();
            System.out.println("Line: " + line);
            String[] parts = line.split(" ");

            switch (parts[0]) {
                case "(TASKTYPES":
                    //parseTaskTypes(parts);
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

    //Main classın içine yazmak mantıklı gibi
    public void parseTaskTypes(String[] parts) {   //düzenlencek
        Validator validator = new Validator();

        ArrayList<Task> taskTypesInText = new ArrayList<Task>();
        System.out.println("taskparts");
        for (int i = 1; i < parts.length; i++) {  //0 tasktype yazan eleman

            System.out.println(parts[i]);



        }
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public double getTaskSize() {
        return taskSize;
    }

    public void setTaskSize(double taskSize) {
        this.taskSize = taskSize;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


