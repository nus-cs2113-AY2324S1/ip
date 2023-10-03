package doli.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Subclass of Task, specifying events with a start and ending time */
public class Event extends Task {
    protected LocalDate startTime;
    protected LocalDate endTime;
    protected boolean isDone;
    protected final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public Event(String description, String startTimeInput, String endTimeInput) {
        super(description);
        try {
            this.startTime = LocalDate.parse(startTimeInput);
            this.endTime = LocalDate.parse(endTimeInput);
        } catch(DateTimeException e) {
            System.out.println("Could not parse the dates.");
        }
        this.isDone = false;
    }
    public LocalDate getStartTime() {
        return startTime;
    }
    public LocalDate getEndTime() {
        return endTime;
    }
    public String getTime() {
        String timeInterval = startTime + " - " + endTime;
        return timeInterval;
    }
    public void setStartTime(String startTime) {
        try {
            this.startTime = LocalDate.parse(startTime);
        } catch(DateTimeException e) {
            System.out.println("Could not parse the start-time.");
        }
    }
    public void setEndTime(String endTime) {
        try {
            this.endTime = LocalDate.parse(endTime);
        } catch(DateTimeException e) {
            System.out.println("Could not parse the start-time.");
        }
    }
    public void setTime(String startTime, String endTime) {
        try {
            this.startTime = LocalDate.parse(startTime);
            this.endTime = LocalDate.parse(endTime);
        } catch(DateTimeException e) {
            System.out.println("Could not parse the dates.");
        }
    }
    public void setDone(boolean done) {
        this.isDone = done;
    }
    public boolean isDone() {
        return isDone;
    }
    @Override
    public String toString() {
        String summary = String.format("[E] %s (%s, %s)", super.toString(), // E stands for Event
                "from: " + DATE_FORMATTER.format(startTime),
                "to: " + DATE_FORMATTER.format(endTime));
        return summary;
    }
}
