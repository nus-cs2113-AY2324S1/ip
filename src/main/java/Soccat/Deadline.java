package Soccat;

public class Deadline extends Task {

    public static final String TASK_CHAR = "D";
    public static final int BY_IDX = 3;
    protected String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public String toTokenString() {
        String description = super.getName() + Task.SPLIT_CHAR + by;
        if (super.getDone()) {
            return TASK_CHAR + Task.SPLIT_CHAR + Task.DONE_CHAR + Task.SPLIT_CHAR + description + "\n";
        } else {
            return TASK_CHAR + Task.SPLIT_CHAR + Task.NOT_DONE_CHAR + Task.SPLIT_CHAR + description + "\n";
        }
    }

    @Override
    public String toString() {
        String deadline = " (by: " + by + ")";
        return "[" + TASK_CHAR + "]" + super.toString() + deadline;
    }
}