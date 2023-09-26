package rene.task;

import java.time.LocalDateTime ;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    public Event(String description, LocalDateTime  startTime, LocalDateTime  endTime){
        super(description, TaskType.EVENT);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getTaskTiming(boolean useDefaultTiming) {
        String startTiming;
        String endTiming;
        if(useDefaultTiming){
            startTiming = startTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
            endTiming = endTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        } else {
            startTiming = startTime.format(DateTimeFormatter.ofPattern("E, MMM dd, yyyy hh a"));
            endTiming = endTime.format(DateTimeFormatter.ofPattern("E, MMM dd, yyyy hh a"));
        }
        return "(from: " + startTiming + " to: " + endTiming + ")";
    }
}
