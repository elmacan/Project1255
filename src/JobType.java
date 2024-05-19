import java.util.ArrayList;

public class JobType {

    private String jobTypeID; //J1,J2 olan
    private ArrayList<JobTypeTask> jobTypeTasks = new ArrayList<JobTypeTask>();  //each job type is a sequence of tasks that must be executed one at a time
    private int currentTaskIndex;
    //The next task in sequence for that jobtype cannot start before the current one finishes.


    @Override
    public String toString() {
        String result = "Tasks:\n";
        for (JobTypeTask jobTypeTask : jobTypeTasks) {
            result += jobTypeTask.toString() + "\n";
        }
        return "jobType "+jobTypeID+ "\n"+result;

    }
    public JobType(String jobTypeID, ArrayList<JobTypeTask> jobTypeTasks, int currentTaskIndex) {
        this.jobTypeID = jobTypeID;
        this.jobTypeTasks = jobTypeTasks;
        this.currentTaskIndex = currentTaskIndex;
    }


    public String getJobTypeID() {
        return jobTypeID;
    }

    public void setJobTypeID(String jobTypeID) {
        this.jobTypeID = jobTypeID;
    }

    public ArrayList<JobTypeTask> getTasks() {
        return jobTypeTasks;
    }

    public void setTasks(ArrayList<JobTypeTask> jobTypeTasks) {
        this.jobTypeTasks = jobTypeTasks;
    }

    public int getCurrentTaskIndex() {
        return currentTaskIndex;
    }

    public void setCurrentTaskIndex(int currentTaskIndex) {
        this.currentTaskIndex = currentTaskIndex;
    }

    public JobType() {

    }

}


   /* public Task getNextTask(Task tasks) {
        int i = currentTaskIndex + 1;
        return getTasks().get(i);
    }

    public void printJobState(String status){
        System.out.println("Job ID: " +  this.jobTypeID );
        System.out.println("Current task: " + getTasks().get(currentTaskIndex));
        System.out.println("Job status: " + this.status);
    }
    //public void jobOrder(Job job, int startTime) {
      //  Task firstTask = job.getNextTask();
        //Station station = executionStation(firstTask);  // Method to find a suitable station
       // int completionTime = completetionTime(firstTask, station, startTime);

        //job.setCurrentStation(station);  /
       // eventQueue.add(new Event(Event.EventType.TASK_COMPLETE, completionTime, job));
   // }

    public void deadlinePassedOrNot(){ //deadline before/after
        if(this.completeTime>this.deadline){
            System.out.println("The job exceeded its deadline by: " + (this.completeTime-this.deadline));
        }else if(this.completeTime<this.deadline){
            System.out.println("The job completed early:  " + (this.deadline-this.completeTime));
        }
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
*/
