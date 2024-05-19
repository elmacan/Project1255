public class JobTypeTask {
    private String taskType; //TaskType tasktype mı emin değilim
    private double taskSize;
    private String status; // add this field



    public String toString() {
        return taskType+" Size: "+taskSize;
    }
    public JobTypeTask(){

    }
    public JobTypeTask(String taskType, double taskSize) {
        this.taskType = taskType;
        this.taskSize = taskSize;
        this.status = "pending"; // initial status

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
    public String getStatus() { // add this method
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void jobOrder(Job job, int startTime) {
        TaskType firstTask = job.getNextTask();
        Station station = executionStation(firstTask);  // Method to find a suitable station
        int completionTime = job.completeTime(firstTask, station, startTime);

        job.getCurrentStation(station);
    }


}


