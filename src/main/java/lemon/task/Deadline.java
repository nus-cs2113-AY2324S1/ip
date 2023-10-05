package lemon.task;

public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String toFile() {
        return "D" + super.toFile() + " | " + by;
    }
}
