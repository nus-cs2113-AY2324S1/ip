package duke.task;

/**
 * Type of task that represents a deadline with end time.
 */
public class Deadline extends Task {
    private String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getFormattedTask() {
        return "[D] " + super.getFormattedTask() + " (by: " + by  + ")";
    }

    @Override
    public String getSerializedString() {
        return "D|" + super.getSerializedString() + "|" + by;
    }
}
