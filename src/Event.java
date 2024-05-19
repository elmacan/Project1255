public class Event {
    private int startTime;
    private String eventType; // (job arrives,completed vb.)
    private String details;
    private long timestamp;

    public enum EventType {
        JOB_ARRIVAL, TASK_START, TASK_COMPLETION, JOB_COMPLETION
    }
}

