package dude;

public class Event extends Task {
    String startDate;
    String endDate;

    // Constructor to initialize an event with a description, start date, and end date
    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = "[E]";  // Type [E] indicates this is an dude.Event
    }

    @Override
    public String toString() {
        return getType() + getStatusIcon() + " " + description + " (from: " + startDate + " to: " + endDate + ")";
    }
}
