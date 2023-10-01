package jarvis.tasks;

public class Deadline extends Task {

    protected String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }

    @Override
    public String getTaskType() {
        return "D";
    }
}