//package src.main.java;
//import src.main.frontend.*;

public class Event extends Task {
    protected String from;
    protected String to;
    public Event (String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "]" + description +
                " (from:" + getFrom() + "to:" + getTo() + ")";
    }
}
