package Task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }

    public String toFile() {
        return "D," + (isCompleted() ? "1" : "0") + "," + getName() + "," + getBy();
    }
}
