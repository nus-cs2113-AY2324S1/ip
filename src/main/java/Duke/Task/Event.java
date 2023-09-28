package Duke.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    protected LocalDate startDateTime;
    protected LocalDate endDateTime;
    public Event(String description, LocalDate startDateTime, LocalDate endDateTime) {
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
