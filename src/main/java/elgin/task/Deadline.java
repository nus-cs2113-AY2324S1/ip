package elgin.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        type = "D";
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description);
        type = "D";
        this.by = by;
        setIsDone(isDone);
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + getBy() + ")";
    }
}
