import java.util.ArrayList;

public class Job {
    protected double duration;
    protected String jobType;
    protected ArrayList<Task> tasks=new ArrayList<Task>();
    public  Job(double duration, String jobType, ArrayList<Task> tasks){
        this.duration = duration;
        this.jobType = jobType;
        this.tasks = tasks;
    }


}
