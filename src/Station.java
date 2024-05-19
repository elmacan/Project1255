import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Station {
    private String stationID;
    private int maxCapacity; //how many jobs it can do
    private boolean multiFlag; // station can do more than one job?
    private boolean fifoFlag;   //waiting tasks are picked using first come first served strategy or earliest job deadline first strategy.
    private String status;
    private List<StationTask> stationTasks=new ArrayList<>();
    //private List<StationTask> currentTasks = new ArrayList<StationTask>();  //?
    //private List<StationTask> waitingTasks = new ArrayList<StationTask>();  //?
    //private List<StationTask> completedTasks; //?

    public List<StationTask> getStationTasks() {
        return stationTasks;
    }

    public Station(){


    }
    public void setStationTasks(List<StationTask> stationTasks) {
        this.stationTasks = stationTasks;
    }

    public Station(String stationID, int maxCapacity, boolean multiFlag, boolean fifoFlag, String status, List<StationTask> stationTasks) {
        this.stationID = stationID;
        this.maxCapacity = maxCapacity;
        this.multiFlag = multiFlag;
        this.fifoFlag = fifoFlag;
        this.status = status;
        this.stationTasks = stationTasks;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}