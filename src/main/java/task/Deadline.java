package task;

import task.Task;

public class Deadline extends Task {
    protected String deadline;
    public Deadline(String description, String taskType, String deadline) {
        super(description, taskType);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    public String toString() {
        return taskType + super.toString() + deadline;
    }
}
