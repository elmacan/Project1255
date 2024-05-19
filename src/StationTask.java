public class StationTask {
    private String taskTypeID; //TaskType tasktype mı tam emin değilim
    private double stationSpeed; //station speed o task için
    private Double plusMinus;
    private static final Random random = new Random();


    @Override
    public String toString() {
        return " " + taskTypeID +
                " taskTypeSpeed: " + stationSpeed +
                " plusMinus " + plusMinus;

    }

    public StationTask(String taskTypeID, double stationSpeed, Double plusMinus) {
        this.taskTypeID = taskTypeID;
        this.stationSpeed = stationSpeed;
        this.plusMinus = plusMinus;
    }
    public double calculateTaskDuration(double taskSize){
        double defaultspeed = stationSpeed;
        if(plusMinus != null){
            double min = stationSpeed*(1-plusMinus/100.0);
            double max= stationSpeed*(1+plusMinus/100.0);
            defaultspeed=min+(max-min)*random.nextDouble();
        }
        return taskSize/defaultspeed;
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

    public double getStationSpeed() {
        return stationSpeed;
    }

    public void setStationSpeed(double stationSpeed) {
        this.stationSpeed = stationSpeed;
    }
}

    public static double calculateTaskDuration(Station station) {
        //Station stationT = station.ge
        return 0.0;
    }
}

