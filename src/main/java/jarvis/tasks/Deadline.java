package jarvis.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime dueTime;

    public Deadline(String description, LocalDateTime dueTime) {
        super(description, TaskType.DEADLINE);
        this.dueTime = dueTime;
    }

    @Override
    public String getTime() {
        String deadlineTiming;
        deadlineTiming = dueTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        return deadlineTiming;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueTime + ")";
    }
}