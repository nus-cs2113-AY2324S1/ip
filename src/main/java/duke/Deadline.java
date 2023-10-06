package duke;
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return "Deadline";
    }

    // Getter method for 'by' date
    public String getBy() {
        return by;
    }

    @Override
    public String toFileString() {
        String doneStatus = isDone ? "1" : "0";
        return "D | " + doneStatus + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
