package main.java.duke.util.task;

public class Event extends Task {
    private String fromDate;
    private String toDate;
    public Event(String name, String fromDate, String toDate) {
        super(name);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDate + " to: " + toDate + ")";
    }

    // String representation for storing in file
    @Override
    public String toStringFile() {
        return "E|" + super.toStringFile() + "|" + this.fromDate + "|" + this.toDate;
    }
}
