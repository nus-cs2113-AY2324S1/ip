package Duke.tasks;

import Duke.Task;

public class Event extends Task {
    String eventTime;
    public Event(String description, String eventTime){
        super(description);
        this.eventTime = eventTime;
    }

    public String getEventTime(){
        return eventTime;
    }

    public String toString() {
        return "[E]" + super.toString() + "(from: " + eventTime + ")";
    }
}
