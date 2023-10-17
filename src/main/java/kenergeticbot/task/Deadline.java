package kenergeticbot.task;

/**
 * Represents a Deadline Task
 * Contains Task Deadline, Task Description and Task Type = [T]
 */
public class Deadline extends Task {
    protected String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        this.taskType = "[D]";
    }
    public Deadline(String description, String deadline, boolean isDone) {
        super(description);
        this.deadline = deadline;
        this.taskType = "[D]";
        this.isDone = isDone;
    }

    public String getDeadline() {
        return deadline;
    }

    public String toString() {
        return taskType + super.toString() + deadline;
    }
    public String printTaskToSave() {
        String task = taskType.replace("[", "").replace("]","");
        return  task + super.printTaskToSave() + " | " + deadline + "\n";
    }
}
