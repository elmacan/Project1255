import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Job {
    private String jobID; //Job1 , Job2
    private JobType jobType;
    private int startTime;
    private int duration;  //Each job has a duration (minutes) in which it must be completed once it arrives.
    private int deadline; // =duration+startTime
    private int completeTime;
    private String status;
    private int currentTaskIndex;
    private JobTypeTask currentJobTypeTask;
    private int currentTime; //bir seye gore yapilmasi lazim






    @Override
    public String toString() {

        return "jobID "+jobID+"\n"+jobType+"JOB start time: "+startTime+" duration: "+duration;
    }


    public Job(String jobID, JobType jobType, int startTime, int duration, JobTypeTask currentJobTypeTask) {

        this.jobID = jobID;
        this.jobType = jobType;
        this.startTime = startTime;
        this.duration = duration;
        this.deadline = startTime + duration;this.deadline = startTime + duration;
        this.currentJobTypeTask = currentJobTypeTask;
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
    public void  JobStateTrack(JobTypeTask currentTask){
        if(startTime< currentTime){
            this.status="Waiting to Start";
            System.out.println("Job " + jobID + " is waiting to start.");
        }else if(startTime == currentTime){
            this.status="In progress";
            System.out.println("Job "+ jobID + " is being executed.");
        }else{
            this.status = "complete";
            currentTaskIndex++;
            System.out.println("Job "+ jobID + " is complete.");
        }
    }
    public void printJobState(String status, JobTypeTask currentJobTypeTask){
        System.out.println("Job ID: " +  this.jobID );
        System.out.println("Current task: " + currentJobTypeTask);
        System.out.println("Job status: " + this.status);
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

    public JobTypeTask getCurrentTask() {
        return currentJobTypeTask;
    }

    public void setCurrentTask(JobTypeTask currentTask) {
        this.currentJobTypeTask = currentTask;
    }
    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }




}
