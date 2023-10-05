package torchie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    private final String DATE_TIME_FORMAT = "MMM d yyyy, HH:mm";

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String formatOutput(LocalDateTime d) {
        return d.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (from: " + formatOutput(startTime) + " to: " + formatOutput(endTime) + ")");
    }

    @Override
    public void announceTaskAdd(){
        super.announceTaskAdd();
        printTask(this.toString());
    }

    @Override
    public String toFileFormat() {
        return ("E | " + super.toFileFormat() + " | /from " + getStartTime() + " /to " + getEndTime());
    }

    public void announceTaskDelete() {
        super.announceTaskDelete();
        super.printTask(this.toString());
    }
}
