package kenergeticbot.task;

public class Deadline extends Task {
    protected String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        this.taskType = "[D]";
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
