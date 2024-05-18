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



    public Job(){

    }



    public Job(String jobID, String jobType, ArrayList<Task> tasks, int currentTaskIndex, int duration, int startTime, int deadline, int completeTime, String status, int currentTime) {
        this.jobID = jobID;
        this.jobType = jobType;
        this.tasks = tasks;
        this.currentTaskIndex = currentTaskIndex;
        this.duration = duration;
        this.startTime = startTime;
        this.deadline = duration+startTime;
        this.completeTime = completeTime;
        this.status = status;
        this.currentTime = currentTime;
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
    public void printJobState(String status){
        System.out.println("Job ID: " +  this.jobID );
        System.out.println("Current task: " + getTasks().get(currentTaskIndex));
        System.out.println("Job status: " + this.status);
    }
    /*public void jobOrder(Job job, int startTime) {
        Task firstTask = job.getNextTask();
        Station station = executionStation(firstTask);  // Method to find a suitable station
        int completionTime = calculateTaskCompletionTime(firstTask, station, startTime);

        job.setCurrentStation(station);
        eventQueue.add(new Event(Event.EventType.TASK_COMPLETE, completionTime, job));
    }*/

    public void deadlinePassedOrNot(){ //deadline before/after
        if(this.completeTime>this.deadline){
            System.out.println("The job exceeded its deadline by: " + (this.completeTime-this.deadline));
        }else if(this.completeTime<this.deadline){
            System.out.println("The job completed early:  " + (this.deadline-this.completeTime));
        }
    }
    public void printTasks(){
        System.out.println();
        System.out.println("jobtype: "+this.jobType);
        for(int i=0;i<tasks.size();i++){

            System.out.print("   task: "+tasks.get(i).getTaskType());
            System.out.println("   size: "+tasks.get(i).getTaskSize());

        }

    }
    public String stateOfJob() { //Jobun sa
        if(this.currentTime<this.startTime) {
            return this.status = "Waiting to Start";
        }else if((this.currentTime>this.startTime)&&(this.currentTime<=this.completeTime)){
            return this.status = "In Progress";
        }else{
            return this.status = "Completed";
        }
    }
}

