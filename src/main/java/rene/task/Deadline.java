package rene.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Represents deadline tasks in the current task list
 */
public class Deadline extends Task {
    private LocalDateTime dueTime;
    /**
     * Creates a new task of type deadline.
     *
     * @param description Task description.
     * @param dueTime The deadline of the task.
     */
    public Deadline(String description, LocalDateTime dueTime){
        super(description, TaskType.DEADLINE);
        this.dueTime = dueTime;
    }
    /**
     * Returns the due time of the deadline
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
        String deadlineTiming;
        if(useDefaultTiming){
            deadlineTiming = dueTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        } else {
            deadlineTiming = dueTime.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm"));
        }
        return "(by: " + deadlineTiming + ")";
    }

}
