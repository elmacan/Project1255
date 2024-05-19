import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Station {
    private String stationID;
    private int maxCapacity; //how many jobs it can do
    private boolean multiFlag; // station can do more than one job?
    private boolean fifoFlag;   //waiting tasks are picked using first come first served strategy or earliest job deadline first strategy.
    private List<StationTask> stationTasks=new ArrayList<>();
    private String status;


    public StationTask getStationTaskByID(String taskTypeID){
        for(StationTask stationTask: stationTasks) {
            if(stationTask.getTaskTypeID().equals(taskTypeID)) {
                return stationTask;
            }
            System.out.println("There is no such a task in this station!");
            return null;
        }
    }

    //private List<StationTask> currentTasks = new ArrayList<StationTask>();  //?
    //private List<StationTask> waitingTasks = new ArrayList<StationTask>();  //?
    //private List<StationTask> completedTasks; //?


    // maxcapacitye ulaşılmamışsa task ekle

    /*public boolean isStationAvailable(){
        return currentTasks.size()<maxCapacity;

    }

    public double getSpeedForTask(JobTypeTask jobTypeTask) {
        if (this.plusMinus != null) {
            return getRandomSpeed();
        } else {
            return speedForThatTask;
        }
    }


    public boolean canHandleTaskType(String taskType) {
        return completedTasks.contains(taskType);
    }
    public void updateStatus() {
        status = currentTasks.isEmpty() ? "idle" : "busy";
    }




    public void addTask(JobTypeTask jobTypeTask) {

        if (isStationAvailable()) {
            currentTasks.add(jobTypeTask);
            jobTypeTask.start(getSpeedForTask(jobTypeTask),stationID);
        } else {
            waitingTasks.add(jobTypeTask);//hhjg
        }

    }
    // waitingden removela currenttaska ekle
    public void processQueue() {
        while (currentTasks.size() < maxCapacity && !waitingTasks.isEmpty()) {
            JobTypeTask jobTypeTask = waitingTasks.remove(0);
            currentTasks.add(jobTypeTask);
            jobTypeTask.start(getRandomSpeed(),stationID);
        }
    }
    public void stationStatus() {
        if (isStationAvailable()) {
            status = "waiting to be executed";
        }else{
            status = "in execution";
        }
    }

    //random speed maxla min arasındaki ilişki ne?

    public double getRandomSpeed() {
        Random random = new Random();
        double minSpeed = speedForThatTask * (1 - plusMinus);
        double maxSpeed = speedForThatTask * (1 + plusMinus);
        return minSpeed + (maxSpeed - minSpeed) * random.nextDouble();//
    }

    public void completeTask(JobTypeTask jobTypeTask){
        currentTasks.remove(jobTypeTask);
        jobTypeTask.complete(); /// current taskde tamamlanan gidiyor
        processQueue();
    }*/


    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public boolean isMultiFlag() {
        return multiFlag;
    }

    public void setMultiFlag(boolean multiFlag) {
        this.multiFlag = multiFlag;
    }

    public boolean isFifoFlag() {
        return fifoFlag;
    }

    public void setFifoFlag(boolean fifoFlag) {
        this.fifoFlag = fifoFlag;
    }

    public double getSpeedForThatTask() {
        return speedForThatTask;
    }

    public void setSpeedForThatTask(double speedForThatTask) {
        this.speedForThatTask = speedForThatTask;
    }

    public double getPlusMinus() {
        return plusMinus;
    }

    public void setPlusMinus(double plusMinus) {
        this.plusMinus = plusMinus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<JobTypeTask> getCurrentTasks() {
        return currentTasks;
    }

    public void setCurrentTasks(ArrayList<JobTypeTask> currentJobTypeTasks) {
        this.currentTasks = currentJobTypeTasks;
    }

    public ArrayList<JobTypeTask> getWaitingTasks() {
        return waitingTasks;
    }

    public void setWaitingTasks(ArrayList<JobTypeTask> waitingJobTypeTasks) {
        this.waitingTasks = waitingJobTypeTasks;
    }

    public List<String> getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(List<String> completedTasks) {
        this.completedTasks = completedTasks;
    }
}