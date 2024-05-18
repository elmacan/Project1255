import java.util.*;

public class Station {
    private String stationID;
    private int maxCapacity; //how many jobs it can do
    private boolean multiFlag; // station can do more than one job?
    private boolean fifoFlag;   //waiting tasks are picked using first come first served strategy or earliest job deadline first strategy.
    private double speedForThatTask;
    private double plusMinus; //değer yoksa constant speed
    private String status;
    private ArrayList<Task> currentTasks = new ArrayList<Task>();  //o sırada execute olan
    //private ArrayList<Task> waitingTasks = new ArrayList<Task>();  //execute olmayı bekleyen
    // waiting taski priority queue yapıyorum.
    private ArrayList<Task> waitingTasks= new ArrayList<Task>();
    private List<String> taskTypesHandled;

    public Station(String stationID,int maxCapacity,boolean multiFlag,boolean fifoFlag,double speedForThatTask,double plusMinus){
        this.stationID = stationID;
        this.maxCapacity = maxCapacity;
        this.multiFlag = multiFlag;
        this.fifoFlag = fifoFlag;
        this.speedForThatTask = speedForThatTask;
        this.plusMinus = plusMinus;
        this.status = "idle";

        //Comparator<Task> comparator = fifoFlag ? Comparator.comparingInt(Task::g) : Comparator.comparingInt(Task::);
        //this.waitingTasks = new PriorityQueue<>(comparator);

    }


    // maxcapacitye ulaşılmamışsa task ekle

    public boolean isStationAvailable(){
        return currentTasks.size()<maxCapacity;

    }

// stationa task ekleyip status değişiyo
    public void addTask(Task task) {

        if (isStationAvailable()) {
            currentTasks.add(task);
            task.start(getRandomSpeed());
            updateStatus();

        } else {
            waitingTasks.add(task);
            task.waitingTaskStatus();
        }
    }
    // status güncelleme
    public void updateStatus(){
        status= currentTasks.isEmpty() ? "idle" : "busy";
    }
    // waitingden removela currenttaska ekle
    public void processQueue() {
        while (currentTasks.size() < maxCapacity && !waitingTasks.isEmpty()) {
            Task task = waitingTasks.remove(0);
            currentTasks.add(task);
            task.start(getRandomSpeed());
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
}