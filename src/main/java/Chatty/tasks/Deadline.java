package Chatty.tasks;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String getDescription() {
        return "[D][" + getStatusIcon() + "] " + super.getDescription() + " (by: " + by + ")";
    }
}
