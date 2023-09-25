package data.task;

/**
 * Represents a Deadline in the TaskList.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline (String description, String by, int setMark) {
        super(description, setMark);
        this.by = by;
    }

    @Override
    public String getDetails() {
        return "[D]" + super.getDetails() + " (by: " + by + ")";
    }

    public String getBy() {
        return by;
    }
}