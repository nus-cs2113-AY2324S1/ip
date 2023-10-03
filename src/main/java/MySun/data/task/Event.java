package MySun.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end){
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event (String description, LocalDateTime start, LocalDateTime end, int setMark) {
        super(description, setMark);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + " (from: " + getFormattedStart() + "to: " + getFormattedEnd() + ")";
    }

    public String getFormattedStart() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h.mma dd MMMM, yyyy");
        return start.format(formatter);
    }

    public String getFormattedEnd() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h.mma dd MMMM, yyyy");
        return end.format(formatter);
    }
}
