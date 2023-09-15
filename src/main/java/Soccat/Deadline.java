package Soccat;

public class Deadline extends Task {

    protected String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        String deadline = " (by: " + by + ")";
        return "[D]" + super.toString() + deadline;
    }
}