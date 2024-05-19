import java.util.*;

public class Station {
    private String stationID;
    private int maxCapacity; //how many jobs it can do
    private boolean multiFlag; // station can do more than one job?
    private boolean fifoFlag;   //waiting tasks are picked using first come first served strategy or earliest job deadline first strategy.
    private double speedForThatTask;
    private double plusMinus; //değer yoksa constant speed
    private String status;
    //private ArrayList<String>
    private ArrayList<JobTypeTask> currentJobTypeTasks = new ArrayList<JobTypeTask>();  //o sırada execute olan
    //private ArrayList<Task> waitingTasks = new ArrayList<Task>();  //execute olmayı bekleyen
    // waiting taski priority queue yapıyorum.
    private ArrayList<JobTypeTask> waitingJobTypeTasks = new ArrayList<JobTypeTask>();
    private List<String> taskTypesHandled;

    public Station(){

    }
    public Station(String stationID,int maxCapacity,boolean multiFlag,boolean fifoFlag,double speedForThatTask,double plusMinus){
        this.stationID = stationID;
        this.maxCapacity = maxCapacity;
        this.multiFlag = multiFlag;
        this.fifoFlag = fifoFlag;
        this.speedForThatTask = speedForThatTask;
        this.plusMinus = plusMinus;
        this.status = "idle";


    }


    // maxcapacitye ulaşılmamışsa task ekle

    public boolean isStationAvailable(){
        return currentJobTypeTasks.size()<maxCapacity;

    }

// stationa task ekleyip status değişiyo
    public void addTask(JobTypeTask jobTypeTask) {

        if (isStationAvailable()) {
            currentJobTypeTasks.add(jobTypeTask);
            jobTypeTask.start(getRandomSpeed());
            updateStatus();

        } else {
            waitingJobTypeTasks.add(jobTypeTask);
            jobTypeTask.waitingTaskStatus();
        }
    }
    // status güncelleme
    public void updateStatus(){
        status= currentJobTypeTasks.isEmpty() ? "idle" : "busy";
    }
    // waitingden removela currenttaska ekle
    public void processQueue() {
        while (currentJobTypeTasks.size() < maxCapacity && !waitingJobTypeTasks.isEmpty()) {
            JobTypeTask jobTypeTask = waitingJobTypeTasks.remove(0);
            currentJobTypeTasks.add(jobTypeTask);
            jobTypeTask.start(getRandomSpeed());
        }
        updateStatus();
    }

    public boolean canHandleTaskType(String taskType) {
        return taskTypesHandled.contains(taskType);
    }

    //random speed maxla min arasındaki ilişki ne?

    public double getRandomSpeed() {
        Random random = new Random();
        double minSpeed = speedForThatTask * (1 - plusMinus);
        double maxSpeed = speedForThatTask * (1 + plusMinus);
        return minSpeed + (maxSpeed - minSpeed) * random.nextDouble();//
    }


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
        return currentJobTypeTasks;
    }

    public void setCurrentTasks(ArrayList<JobTypeTask> currentJobTypeTasks) {
        this.currentJobTypeTasks = currentJobTypeTasks;
    }

    public ArrayList<JobTypeTask> getWaitingTasks() {
        return waitingJobTypeTasks;
    }

    public void setWaitingTasks(ArrayList<JobTypeTask> waitingJobTypeTasks) {
        this.waitingJobTypeTasks = waitingJobTypeTasks;
    }
}