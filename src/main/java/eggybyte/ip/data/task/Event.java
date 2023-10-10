package eggybyte.ip.data.task;

import eggybyte.ip.data.exception.TipsException;
import eggybyte.ip.data.DateTime;

/**
 * A child class of Todo that has 2 new variables 'from' and 'to' to show the
 * duration that the task is going to last.
 */
public class Event extends Todo {
    protected final static String type = "E";
    public DateTime from, to;

    /**
     * Creating a new Event.
     */
    public Event(String description, String from, String to) throws TipsException {
        super(description, type);
        this.from = new DateTime(from);
        this.to = new DateTime(to);
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + ", to: " + to + ")";
    }
}