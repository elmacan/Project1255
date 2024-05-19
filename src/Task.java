import java.util.ArrayList;
public class Task {
    private String taskType; //TaskType tasktype mı emin değilim
    private double taskSize;

    public String toString() {
        return taskType+" Size: "+taskSize;
    }
    public Task(){

    }
    public Task(String taskType, double taskSize) {
        this.taskType = taskType;
        this.taskSize = taskSize;

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


}


