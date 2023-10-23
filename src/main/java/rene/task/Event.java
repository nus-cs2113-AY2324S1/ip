package rene.task;

import java.time.LocalDateTime ;
import java.time.format.DateTimeFormatter;
/**
 * Represents event tasks in the current task list
 */
public class Event extends Task{
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    /**
     * Creates a new task of type event.
     *
     * @param description Task description.
     * @param startTime The starting time of event.
     * @param endTime The ending time of event.
     */
    public Event(String description, LocalDateTime  startTime, LocalDateTime  endTime){
        super(description, TaskType.EVENT);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the start and end time of the event
     * in a specially formatted style.
     *
     * @param useDefaultTiming If true, uses the default
     *                         date time format (dd-MM-yyyy HH:mm)
     *                         used to store date time data in the
     *                         hard disk. Else, uses the date time format
     *                         (E, MMM dd, yyyy hh a).
     */
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
