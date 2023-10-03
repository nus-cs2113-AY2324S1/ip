package jarvis.tasks;

public class Deadline extends Task {

    protected String dueTime;

    public Deadline(String description, String dueTime) {
        super(description, TaskType.DEADLINE);
        this.dueTime = dueTime;
    }

    @Override
    public String getTime() {
        return this.dueTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueTime + ")";
    }
}