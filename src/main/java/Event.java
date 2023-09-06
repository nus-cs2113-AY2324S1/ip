public class Event extends Task{
    String eventTime;
    Event(String description, String eventTime){
        super(description);
        this.eventTime = eventTime;
    }

    public String toString() {
        return "[E]" + super.toString() + "(from: " + eventTime + ")";
    }
}
