import java.util.List;

public class Job {
    private String jobID; //Job1 , Job2 olan
    private JobType jobType;
    private int startTime;
    private int duration;  //Each job has a duration (minutes) in which it must be completed once it arrives.
    private int deadline; // =duration+startTime
    private int completeTime; //job ın complete olduğu zaman
    private String status;





    @Override
    public String toString() {

        return "jobID "+jobID+"\n"+jobType+"JOB start time: "+startTime+" duration: "+duration;
    }


    public Job(String jobID, JobType jobType, int startTime, int duration) {

        this.jobID = jobID;
        this.jobType = jobType;
        this.startTime = startTime;
        this.duration = duration;
    }

    public void calculateJobDuration(List<Station> stations) {
        double totalDuration = 0;
        for (Task task : jobType.getTasks()) {
            Station station = findAvailableStationForTask(task, stations);
            if (station != null) {
                double speed = station.getSpeedForThatTask();
                if (station.getPlusMinus() > 0) {
                    speed = station.getRandomSpeed();
                }
                double taskDuration = task.getTaskSize() / speed;
                totalDuration += taskDuration;
            }
        }
    }

    private Station findAvailableStationForTask(Task task, List<Station> stations) {
        for (Station station : stations) {
            if (station.canHandleTaskType(task.getTaskType())) {
                return station;
            }
        }
        System.out.println("Not any available station");
        return null; // exception handling lazım
    }


    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(int completeTime) {
        this.completeTime = completeTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JobType getJobType() {

        return jobType;

    }

}
