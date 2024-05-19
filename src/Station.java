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
    private ArrayList<StationTask> stationTasks = new ArrayList<>();
    private ArrayList<StationTask> waitingQueue = new ArrayList<>();
    private ArrayList<StationTask> tasksInExecution = new ArrayList<>();
    private String stationStatus;


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

    }


    public boolean canHandleTaskType(String taskType) {
        for (StationTask task : stationTasks) {
            if (task.getTaskTypeID().equals(taskType)) {
                return true;
            }
        }
        return false;
    }
    private void updateStationStatus() {
        if (tasksInExecution.isEmpty() && waitingQueue.isEmpty()) {
            stationStatus = "idle";
        } else {
            stationStatus = "busy";
        }
    }

    public ArrayList<StationTask> taskExecution(){
        for (StationTask task : stationTasks) {
            if(canHandleTaskType(task.getTaskTypeID())){
                waitingQueue.add(task);
            }
        }
        return waitingQueue;
    }

    public void printStatus() {
        System.out.println("Station " + stationID + " status:");
        for (StationTask task : tasksInExecution) {
            System.out.println(task);
        }
        System.out.println("Waiting queue:");
        for (StationTask task : waitingQueue) {
            System.out.println(task);
        }
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

    //public void setStationTasks(List<StationTask> stationTasks) {
    //    this.stationTasks = stationTasks;
    //}


    public StationTask getStationTaskByID(String taskTypeID) {
        for (StationTask stationTask : stationTasks) {
            if (stationTask.getTaskTypeID().equals(taskTypeID)) {
                return stationTask;
            }
        }
        System.out.println("There is no such a task in this station!");
        return null;
    }

    public String getStationStatus() {
        return stationStatus;
    }

    public void setStationStatus(String stationStatus) {
        this.stationStatus = stationStatus;
    }
}



