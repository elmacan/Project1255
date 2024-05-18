import java.util.ArrayList;

public class JobType {

    private String jobTypeID; //J1,J2 olan
    private ArrayList<Task> tasks = new ArrayList<Task>();  //each job type is a sequence of tasks that must be executed one at a time
    private int currentTaskIndex;
    //The next task in sequence for that jobtype cannot start before the current one finishes.


    @Override
    public String toString() {
        String result = "Tasks:\n";
        for (Task task : tasks) {
            result += task.toString() + "\n";
        }
        return "jobType "+jobTypeID+ "\n"+result;

    }
    public JobType(String jobTypeID, ArrayList<Task> tasks, int currentTaskIndex) {
        this.jobTypeID = jobTypeID;
        this.tasks = tasks;
        this.currentTaskIndex = currentTaskIndex;
    }
    public void updateJobState(Job job){
        if(currentTaskIndex >= )
    }

    public String getJobTypeID() {
        return jobTypeID;
    }

    public void setJobTypeID(String jobTypeID) {
        this.jobTypeID = jobTypeID;
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

    public JobType() {

    }

    public void printTasks() {
        System.out.println();
        System.out.println("jobtype: " + this.jobTypeID);
        for (int i = 0; i < tasks.size(); i++) {

            System.out.print("   task: " + tasks.get(i).getTaskType());
            System.out.println("   size: " + tasks.get(i).getTaskSize());

        }
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
