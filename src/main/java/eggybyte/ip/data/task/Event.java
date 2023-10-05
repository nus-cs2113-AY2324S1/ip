package eggybyte.ip.data.task;

import eggybyte.ip.data.exception.TipsException;
import eggybyte.ip.data.DateTime;

public class Event extends Todo {
    protected final static String type = "E";
    public DateTime from, to;

    public Event(String description, String from, String to) throws TipsException {
        super(description, type);
        this.from = new DateTime(from);
        this.to = new DateTime(to);
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}