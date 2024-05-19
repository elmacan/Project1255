import java.util.List;

public class Job {
    private String jobID; //Job1 , Job2 olan
    private JobType jobType;
    private int startTime;
    private int duration;  //Each job has a duration (minutes) in which it must be completed once it arrives.
    private int deadline; // =duration+startTime
    private int completeTime; //job ın complete olduğu zaman
    private String status;
    private int currentTaskIndex;
    private JobTypeTask currentJobTypeTask;





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
   public void isDeadlinePassed(){
        if (this.duration>this.deadline){
            int a = this.duration - this.deadline;
            System.out.println("This task has exceeded its deadline by: " + a);

        }else if(this.duration<this.deadline){
            int a = this.deadline - this.duration;
            System.out.println("This task finished early by " + a);
        }
    }
    public void  JobStateTrack(){
        if(currentTaskIndex< jobType.getTasks().size()){
            this.status="Waiting to Start";
            System.out.println("Job " + jobID + " is waiting to start.");
        }else if(currentTaskIndex == jobType.getTasks().size()){
            this.status="In progress";
            System.out.println("Job "+ jobID + " is being executed.");
        }
    }

    public void calculateJobDuration(List<Station> stations) {
        double totalDuration = 0;
        for (JobTypeTask jobTypeTask : jobType.getTasks()) {
            Station station = findAvailableStationForTask(jobTypeTask, stations);
            if (station != null) {
                double speed = station.getSpeedForThatTask();
                if (station.getPlusMinus() > 0) {
                    speed = station.getRandomSpeed();
                }
                double taskDuration = jobTypeTask.getTaskSize() / speed;
                totalDuration += taskDuration;
            }
        }
        /// ceil integera yuvarlıyor
        this.duration= (int)Math.ceil(totalDuration);
        this.deadline=this.startTime +this.duration;

    }

    private Station findAvailableStationForTask(JobTypeTask jobTypeTask, List<Station> stations) {
        for (Station station : stations) {
            if (station.canHandleTaskType(jobTypeTask.getTaskType())) {
                return station;
            }
        }
        System.out.println("Not any available station");
        return null; // exception handling lazım
    }
////// extra status check oldu
 public void jobStatusUpdate(){
        if(currentTaskIndex >= jobType.getTasks().size()){
            this.status ="completed";
            this.completeTime=getCompleteTime();
            System.out.println("Job" + jobID + " has been completed.");

        }
        if(currentJobTypeTask.getStatus().equals("completed")){
            currentTaskIndex++;
            if(currentTaskIndex<jobType.getTasks().size()){
                currentJobTypeTask = jobType.getTasks().get(currentTaskIndex);
                this.status= "waiting";
                System.out.println("Job " + jobID + "is waiting to start task " + currentJobTypeTask.getTaskType());
            }
        }
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

    public int getCurrentTaskIndex() {
        return currentTaskIndex;
    }

    public void setCurrentTaskIndex(int currentTaskIndex) {
        this.currentTaskIndex = currentTaskIndex;
    }

    public JobTypeTask getCurrentTaks() {
        return currentJobTypeTask;
    }

    public void setCurrentTaks(JobTypeTask currentTaks) {
        this.currentJobTypeTask = currentTaks;
    }
}
