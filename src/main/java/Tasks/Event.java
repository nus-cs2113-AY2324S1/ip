package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));;
        this.to = to.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
    }

    public void setTo(LocalDateTime to) {
        this.to = to.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
    }

    @Override
    public String toString() {
        return "\t[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
