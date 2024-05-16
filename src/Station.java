import java.util.ArrayList;
import java.util.Random;

public class Station {
    private String stationID;
    private int maxCapacity; //how many jobs it can do
    private boolean multiFlag; // station can do more than one job?
    private boolean fifoFlag;   //waiting tasks are picked using first come first served strategy or earliest job deadline first strategy.
    private double speedForThatTask;
    private double plusMinus; //değer yoksa constant speed
    private String status;
    private ArrayList<Task> currentTasks = new ArrayList<Task>();  //o sırada execute olan
    private ArrayList<Task> waitingTasks = new ArrayList<Task>();  //execute olmayı bekleyen


    // current taskin sizeı max kapasiteden küçükse işleme alıyo değilse sıraya sokuyor
    public void addTask(Task task) {
        if (currentTasks.size() < maxCapacity) {
            currentTasks.add(task);
            task.start(getRandomSpeed());
        } else {
            waitingTasks.add(task);
        }
    }

    private double getRandomSpeed() {
        // eğer plusminus değeri yoksa çık
        if(plusMinus==0){

        }
        else{
            double minSpeed = speedForThatTask * (1 - plusMinus);
            double maxSpeed = speedForThatTask * (1 + plusMinus);
            return minSpeed + (maxSpeed - minSpeed) * plusMinus;// burası olmadı
        }


        return 0;
    }

    //waitingtasks den task removelama
    public void processQueue() {
        while (currentTasks.size() < maxCapacity && !waitingTasks.isEmpty()) {
            Task task = waitingTasks.remove(0);
            currentTasks.add(task);
            task.start(getRandomSpeed());
        }
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