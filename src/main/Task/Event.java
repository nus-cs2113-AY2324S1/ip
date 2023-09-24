package Task;

import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    
    public String getFrom() {
        return from.format(formatter);
    }

    public String getTo() {
        return to.format(formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFrom() + " to: " + getTo() +  ")";
    }

    public String toFile() {
        return "E," + (isCompleted() ? "1" : "0") + "," + getName() + "," + getFrom()  + "," + getTo();
    }
}
