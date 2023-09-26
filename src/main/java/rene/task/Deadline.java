package rene.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime dueTime;

    public Deadline(String description, LocalDateTime  dueTime){
        super(description, TaskType.DEADLINE);
        this.dueTime = dueTime;
    }

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
