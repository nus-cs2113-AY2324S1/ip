package eggybyte.ip.data.task;

import eggybyte.ip.data.exception.TipsException;
import eggybyte.ip.data.DateTime;

/**
 * A child class of Todo that has a new variable 'by' to show the end of the
 * task.
 */
public class Deadline extends Todo {
    protected final static String type = "D";
    public final DateTime by;

    /**
     * Creating a new Event.
     */
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