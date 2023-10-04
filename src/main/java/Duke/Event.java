package Duke;

public class Event extends Task {

    protected String start;
    protected String end;
    
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        this.type = "E";
    }
    
    @Override
    public String getDescription() {
        return "[" + type + "]" + super.getDescription() + "(from: " + start + "to: " + end + ")";
    }

    @Override
    public String getStart() {
        return start;
    }

    @Override
    public String getEnd() {
        return end;
    }
}
