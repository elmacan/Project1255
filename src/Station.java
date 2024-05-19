import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Station {
    private String stationID;
    private int maxCapacity; //how many jobs it can do
    private boolean multiFlag; // station can do more than one job?
    private boolean fifoFlag;   //waiting tasks are picked using first come first served strategy or earliest job deadline first strategy.
    private List<StationTask> stationTasks = new ArrayList<>();
    private Queue<Job> waitingQueue = new LinkedList<>();
    private List<Job> jobsInExecution = new ArrayList<>();
    private double speedForThatTask; // Base speed for tasks
    private Double plusMinus; // Speed variance percentage (e.g., 0.1 for ±10%)

    public Station(String stationId, int maxCapacity, boolean multiFlag, boolean fifoFlag) {
    }


    @Override
    public String toString() {

        return stationID +
                "  maxCapacity: " + maxCapacity +
                "  multiFlag: " + multiFlag +
                "  fifoFlag: " + fifoFlag +
                " " + stationTasks;

    }

    public Station(String stationID, int maxCapacity, boolean multiFlag, boolean fifoFlag,double speedForThatTask, Double plusMinus) {
        this.stationID = stationID;
        this.maxCapacity = maxCapacity;
        this.multiFlag = multiFlag;
        this.fifoFlag = fifoFlag;
        this.speedForThatTask = speedForThatTask;
        this.plusMinus = plusMinus;
    }
    public double getSpeedForThatTask() {
        if (this.plusMinus != null) {
            return getRandomSpeed();
        } else {
            return speedForThatTask;
        }
    }
    public Double getPlusMinus() {
        return plusMinus;
    }

    double getRandomSpeed() {
        Random random = new Random();
        double minSpeed = speedForThatTask * (1 - plusMinus);
        double maxSpeed = speedForThatTask * (1 + plusMinus);
        return minSpeed + (maxSpeed - minSpeed) * random.nextDouble();
    }

    public boolean canHandleTaskType(String taskType) {
        for (StationTask task : stationTasks) {
            if (task.getTaskTypeID().equals(taskType)) {
                return true;
            }
        }
        return false;
    }


    // stationa task ekleme
    public void addStationTask(StationTask stationTask) {
        this.stationTasks.add(stationTask);
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

    public List<StationTask> getStationTasks() {
        return stationTasks;
    }

    public void setStationTasks(List<StationTask> stationTasks) {
        this.stationTasks = stationTasks;
    }


    public StationTask getStationTaskByID(String taskTypeID) {
        for (StationTask stationTask : stationTasks) {
            if (stationTask.getTaskTypeID().equals(taskTypeID)) {
                return stationTask;
            }
        }
        System.out.println("There is no such a task in this station!");
        return null;
    }


    public void printStatus() {
        System.out.println("Station " + stationID + " status:");
        for (Job job : jobsInExecution) {
            System.out.println(job);
        }
        System.out.println("Waiting queue:");
        for (Job job : waitingQueue) {
            System.out.println(job);
        }
    }



    ///



    //private List<StationTask> currentTasks = new ArrayList<StationTask>();  //?
    //private List<StationTask> waitingTasks = new ArrayList<StationTask>();  //?



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
}



