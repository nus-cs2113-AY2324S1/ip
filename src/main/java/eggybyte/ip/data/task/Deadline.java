package eggybyte.ip.data.task;

public class Deadline extends Todo {
    protected final static String type = "D";
    public final String by;

    public Deadline(String description, String by) {
        super(description, type);
        this.by = by;
        isDone = false;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}