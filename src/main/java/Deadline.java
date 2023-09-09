public class Deadline extends Task {
    String by;

    // Constructor to initialize a deadline with a description and a due date
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "[D]";  // Type [D] indicates this is a Deadline
    }

    @Override
    public String toString() {
        return getType() + getStatusIcon() + " " + description + " (by: " + by + ")";
    }
}
