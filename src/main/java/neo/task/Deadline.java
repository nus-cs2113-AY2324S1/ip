package neo.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String formatTask() {
        return "D / " + super.formatTask() + " / " + by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTimeFormatter(by) + ")";
    }
}
