public class StationTask {
    private String taskTypeID; //TaskType tasktype mı tam emin değilim
    private double stationSpeed; //station speed o task için
    private Double plusMinus;


    @Override
    public String toString() {
        return " " + taskTypeID +
                " taskTypeSpeed: " + stationSpeed +
                " plusMinus " + plusMinus ;

    }

    public StationTask(String taskTypeID, double stationSpeed, Double plusMinus) {
        this.taskTypeID = taskTypeID;
        this.stationSpeed = stationSpeed;
        this.plusMinus = plusMinus;
    }

    public String getTaskTypeID() {
        return taskTypeID;
    }

    public void setTaskTypeID(String taskTypeID) {
        this.taskTypeID = taskTypeID;
    }

    public double getPlusMinus() {
        return plusMinus;
    }

    public void setPlusMinus(double plusMinus) {
        this.plusMinus = plusMinus;
    }
    public static double calculateTaskDuration(Station station){
        Station stationT = station.ge
    }


    public String getTaskTypeID() {
        return taskTypeID;
    }

    public void setTaskTypeID(String taskTypeID) {
        this.taskTypeID = taskTypeID;
    }
}
