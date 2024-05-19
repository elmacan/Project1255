public class TaskType {
    private String taskTypeID;
    private Integer defaultsize; // yoksa null

    public TaskType(String taskTypeID, Integer defaultsize) {
        this.taskTypeID = taskTypeID;
        this.defaultsize = defaultsize;
    }

    public String getTaskTypeID() {
        return taskTypeID;
    }

    public void setTaskTypeID(String taskTypeID) {
        this.taskTypeID = taskTypeID;
    }

    public Integer getDefaultsize() {
        return defaultsize;
    }

    public void setDefaultsize(Integer defaultsize) {
        this.defaultsize = defaultsize;
    }
}
