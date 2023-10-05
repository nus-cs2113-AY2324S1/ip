package eggybyte.ip.data.task;

import eggybyte.ip.data.exception.TipsException;
import eggybyte.ip.data.DateTime;

public class Deadline extends Todo {
    protected final static String type = "D";
    public final DateTime by;

    public Deadline(String description, String by) throws TipsException {
        super(description, type);
        this.by = new DateTime(by);
        isDone = false;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}