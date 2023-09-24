package tasks;

public class Event extends Task {
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        String typeOfTask = "[E]";
        String statusOfTask = "[" + super.getStatusIcon() + "] ";
        String task = super.getDescription() + " (from: " + getStart() + " to: " + getEnd() + ")";
        return typeOfTask + statusOfTask + task;
    }

    @Override
    public String encode() {
        return "Event" + super.encode() + SEPARATOR + getStart() + SEPARATOR + getEnd();
    }
}