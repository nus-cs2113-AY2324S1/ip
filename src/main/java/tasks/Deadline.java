package tasks;

import tasks.Task;

public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("description cannot be empty");
        }
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
