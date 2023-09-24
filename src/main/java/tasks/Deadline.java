package tasks;

public class Deadline extends Task {
    private String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String typeOfTask = "[D]";
        String statusOfTask = "[" + super.getStatusIcon() + "] ";
        String task = super.getDescription() + " (by: " + getDeadline() + ")";
        return typeOfTask + statusOfTask + task;
    }
}