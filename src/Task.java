import java.util.ArrayList;
public class Task {
    private String taskType;
    private double taskSize;
    private double duration;   // =task size/station speed for that task
    private String stationID; //hangi stationda execute olacağı
    private String status;  //wait,execute,complete


    public Task(){

    }

    @Override
    public String toString() {
        return taskType+" Size: "+taskSize;
    }

    public Task(String taskType, double taskSize, double duration, String stationID, String status) {
        this.taskType = taskType;
        this.taskSize = taskSize;
        this.duration = duration;
        this.stationID = stationID;
        this.status = status;
    }
//---------------------------------------
    public void start(double speed) {
        this.duration = taskSize / speed;
        this.status = "execute";
        System.out.println("Task of type " + taskType + " is starting at station " + stationID + ". Duration: " + duration + " minutes.");
    }

    public void completedTasKStatus(){
        setStatus("completed");
    }
    public void waitingTaskStatus(){
        setStatus("waiting task");
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


