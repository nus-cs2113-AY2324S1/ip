package Data;

import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event (String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFromString() {
        return this.from.format(Task.DATE_FORMAT);
    }

    public String getFromStringToSave() {
        return this.from.toString();
    }

    public String getToString() {
        return this.to.format(Task.DATE_FORMAT);
    }

    public String getToStringToSave() {
        return this.to.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getFromString() + " to: " + this.getToString() + ")";
    }
}
