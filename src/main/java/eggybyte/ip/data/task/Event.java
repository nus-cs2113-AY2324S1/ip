package eggybyte.ip.data.task;

public class Event extends Todo {
    protected final static String type = "E";
    public String from, to;

    public Event(String description, String from, String to) {
        super(description, type);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to:" + to + ")";
    }
}