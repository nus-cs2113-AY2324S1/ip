package sun.task;
public class Event extends Task{
    protected String start;
    protected String end;

    public Event(String description, String start, String end){
        super(description);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + " (from: " + start + "to: " + end + ")";
    }
}
