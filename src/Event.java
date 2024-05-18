public class Event {
    private int startTime;
    private String eventType; // (job arrives,completed vb.)
    private String details;

    public enum EventType {
        JOB_ARRIVAL, TASK_START, TASK_COMPLETION, JOB_COMPLETION
    }

    private long timestamp;
    private EventType type;
    private Job job;
    private Task task;
    private Station station;

    public Event(long timestamp, EventType type, Job job, Task task, Station station) {
        this.timestamp = timestamp;
        this.type = type;
        this.job = job;
        this.task = task;
        this.station = station;
    }
}

  /*  public long getTimestamp() {
        return timestamp;
    }

    @Override
    public int compareTo(Event other) {
        return Long.compare(this.timestamp, other.timestamp);
    }
    //private String status;

    public int calculateTaskCompletionTime(Task task, Station station, int startTime) {
        double taskDuration = task.getTaskSize() / station.getSpeedForThatTask();
        return startTime + (int) Math.ceil(taskDuration);
    }


    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}*/
