import java.util.ArrayList;
public class Task {
    private int taskType;
    private int taskSize;

    public Task(int taskType, int taskSize) {
        this.taskType = taskType;
        this.taskSize = taskSize;
    }

    public int getTaskType() {
        return this.taskType;
    }

    public int getTaskSize() {
        return this.taskSize;
    }
}

