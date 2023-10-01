package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");

    public Event(String description, LocalDateTime startDate, LocalDateTime endDate){
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + startDate.format(DTF) + " to: " + endDate.format(DTF) + ")";
    }

    @Override
    public String toFileString(){
        return ("E | " + super.toFileString() + " | " + startDate.format(DTF) + " | " + endDate.format(DTF));
    }
}
