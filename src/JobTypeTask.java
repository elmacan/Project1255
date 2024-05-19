public class JobTypeTask {
    private String taskType; //TaskType tasktype mı emin değilim
    private double taskSize;

    public String toString() {
        return taskType+" Size: "+taskSize;
    }
    public JobTypeTask(){

    }
    public JobTypeTask(String taskType, double taskSize) {
        this.taskType = taskType;
        this.taskSize = taskSize;

    }




//---------------------------------------
   /* public void start(double speed, String stationID) {
        this.duration = taskSize / speed;
        this.status = "execute";
        this.stationID = stationID;
        System.out.println("Task of type " + taskType + " is starting at station " + stationID + ". Duration: " + duration + " minutes.");
    }
    public void complete() {
        this.status = "completed";
        System.out.println("Task of type " + taskType + " has been completed at station " + stationID + ".");
    }



*/

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


