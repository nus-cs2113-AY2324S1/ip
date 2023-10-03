package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end){
        super(description);
        this.start = start;
        this.end = end;
        this.taskType = "E";
    }

    public LocalDateTime getStart(){
        return this.start;
    }

    public LocalDateTime getEnd(){
        return this.end;
    }

    @Override
    public String toString(){
        return String.format("[%s]%s (from: %s to: %s)", this.taskType, super.toString(),
                this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma")),
                this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma")));
    }
}
