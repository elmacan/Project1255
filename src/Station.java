import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Station {
    private String stationID;
    private int maxCapacity; //how many jobs it can do
    private boolean multiFlag; // station can do more than one job?
    private boolean fifoFlag;   //waiting tasks are picked using first come first served strategy or earliest job deadline first strategy.
    private String status;

    //private List<StationTask> currentTasks = new ArrayList<StationTask>();  //?
    //private List<StationTask> waitingTasks = new ArrayList<StationTask>();  //?
    //private List<StationTask> completedTasks; //?


    // maxcapacitye ulaşılmamışsa task ekle

    public boolean isStationAvailable(){
        return currentTasks.size()<maxCapacity;

    }

    public double getSpeedForTask(Task task) {
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




    public void addTask(Task task) {

        if (isStationAvailable()) {
            currentTasks.add(task);
            task.start(getSpeedForTask(task),stationID);
        } else {
            waitingTasks.add(task);//hhjg
        }

    }
    // waitingden removela currenttaska ekle
    public void processQueue() {
        while (currentTasks.size() < maxCapacity && !waitingTasks.isEmpty()) {
            Task task = waitingTasks.remove(0);
            currentTasks.add(task);
            task.start(getRandomSpeed(),stationID);
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

    public void completeTask(Task task){
        currentTasks.remove(task);
        task.complete(); /// current taskde tamamlanan gidiyor
        processQueue();
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

    public ArrayList<Task> getCurrentTasks() {
        return currentTasks;
    }

    public void setCurrentTasks(ArrayList<Task> currentTasks) {
        this.currentTasks = currentTasks;
    }

    public ArrayList<Task> getWaitingTasks() {
        return waitingTasks;
    }

    public void setWaitingTasks(ArrayList<Task> waitingTasks) {
        this.waitingTasks = waitingTasks;
    }

    public List<String> getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(List<String> completedTasks) {
        this.completedTasks = completedTasks;
    }
}