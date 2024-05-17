import java.util.ArrayList;

public class Job {
    private String jobID; //Job1 , Job2 olan
    private String jobType; //J1,J2 olan
    private ArrayList<Task> tasks=new ArrayList<Task>();  //each job type is a sequence of tasks that must be executed one at a time
    private int currentTaskIndex;
    //The next task in sequence for that job cannot start before the current one finishes.
    private int duration;  //Each job has a duration (minutes) in which it must be completed once it arrives.
    private int startTime;
    private int deadline; // =duration+startTime
    private int completeTime; //job ın complete olduğu zaman
    private String status;
    private int currentTime;

    public void printTasks(){
        System.out.println();
        System.out.println("jobtype: "+this.jobType);
        for(int i=0;i<tasks.size();i++){

            System.out.print("   task: "+tasks.get(i).getTaskType());
            System.out.println("   size: "+tasks.get(i).getTaskSize());

        }

    }
    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getCurrentTaskIndex() {
        return currentTaskIndex;
    }

    public void setCurrentTaskIndex(int currentTaskIndex) {
        this.currentTaskIndex = currentTaskIndex;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = this.duration+this.startTime;
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


    public void updateStatus(String newStatus){ //update job status
        this.status = newStatus;
    }
    public void printJobState(String status){
        System.out.println("Job ID: " +  this.jobID );
        System.out.println("Current task: " + this.tasks);
        System.out.println("Task status: " + this.status);
    }
    public void deadlineNotPassed(){
        if(this.completeTime>this.deadline){
            System.out.println("The task exceeded its deadline");
        }else if(this.completeTime<=this.deadline){
            System.out.println("The task completed right on time");
        }
    }
    public String stateOfJob() {
        if(this.currentTime<this.startTime) {
            return this.status = "Waiting to Start";
        }else if((this.currentTime>this.startTime)&&(this.currentTime<=this.completeTime)){
            return this.status = "In Progress";
        }else{
            return this.status = "Completed";
        }
    }
}

