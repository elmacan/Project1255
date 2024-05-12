import java.util.ArrayList;
public class Task {
    private String taskType;
    private double taskSize;
    private double duration;   // =task size/station speed for that task
    private String stationID; //hangi stationda execute olacağı
    private String status;  //wait,execute,complete


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


