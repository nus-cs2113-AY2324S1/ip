package main.java.duke.util.task;

public class Deadline extends Task{
    private String by;
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    // String representation for storing in file
    @Override
    public String toStringFile() {
        return "D|" + super.toStringFile() + "|" + this.by;
    }
}
