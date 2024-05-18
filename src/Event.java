public class Event {
    private int startTime;
    private String eventType; // (job arrives,completed vb.)
    private String details;

    public enum EventType {
        JOB_ARRIVAL, TASK_START, TASK_COMPLETION, JOB_COMPLETION
    }
}

   /* private long timestamp;
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
}*/

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





/* ece
void processEvents() {
    while (!eventQueue.isEmpty()) {
        Event currentEvent = eventQueue.poll();
        System.out.println("Handling event at time: " + currentEvent.time);

        switch (currentEvent.type) {
            case JOB_START:
                handleJobStart(currentEvent.job, currentEvent.time);
                break;
            case TASK_COMPLETE:
                handleTaskCompletion(currentEvent.job, currentEvent.time);
                break;
        }
    }
}

void handleJobStart(Job job, int startTime) {
    // Assign the first task of the job to a suitable station
    // Add a TASK_COMPLETE event based on task duration and station speed
}

void handleTaskCompletion(Job job, int completionTime) {
   job.completeCurrentTask(completionTime);  // Mark the current task as complete
    if (job.hasMoreTasks()) {
        Task nextTask = job.getNextTask();
        Station nextStation = findStationForTask(nextTask);
        int nextCompletionTime = calculateTaskCompletionTime(nextTask, nextStation, completionTime);

        job.setCurrentStation(nextStation);
        eventQueue.add(new Event(Event.EventType.TASK_COMPLETE, nextCompletionTime, job));
    } else {
        job.setCompletionTime(completionTime);
        System.out.println("Job " + job.getId() + " completed at time " + completionTime);
    }
}

 */

/* ece2
void printSystemState() {
    // Print current state of jobs and stations
}

void calculateStatistics() {
    double totalTardiness = 0;
    int tardyJobs = 0;
    for (Job job : jobs) {
        if (job.getCompletionTime() > job.getDeadline()) {
            totalTardiness += job.getCompletionTime() - job.getDeadline();
            tardyJobs++;
        }
    }
    double averageTardiness = tardyJobs > 0 ? totalTardiness / tardyJobs : 0;
    System.out.println("Average Tardiness: " + averageTardiness);
    calculateStationUtilization();
}
void calculateStationUtilization() {
    for (Station station : stations) {
        double utilization = (double) station.getActiveTime() / (currentSimulationTime - simulationStartTime);
        System.out.println("Station " + station.getId() + " Utilization: " + (utilization * 100) + "%");
    }

 */