package Duke.Task;

public class Event extends Task {
    protected String startDateTime;
    protected String endDateTime;
    public Event(String description, String startDateTime, String endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        taskType = "Event";
        symbol = "E";
    }

    @Override
    public String convertToSaveFormat() {
        String doneMarker = isDone ? "1" : "0";
        return symbol + " | " + doneMarker + " | " + description + " /from " + startDateTime + " /to " + endDateTime;
    }

    @Override
    public String toString() {
        return "\t[E]" + super.toString() + " (from:" + this.startDateTime + " to:" + this.endDateTime + ")";
    }
}
