package data.task;

public class Event extends Task {

    protected String start;
    protected String end;
    public Event (String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event (String description, String start, String end, int setMark) {
        super(description, setMark);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getDetails() {
        return "[E]" + super.getDetails() + " (from: " + start + " to: " + end + ")";
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
