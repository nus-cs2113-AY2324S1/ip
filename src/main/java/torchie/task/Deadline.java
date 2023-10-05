package torchie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    private final String DATE_TIME_FORMAT = "MMM d yyyy, HHmm";

    public Deadline(String description, LocalDateTime deadline){
        super(description);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public String formatOutput(LocalDateTime d) {
        return d.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: " + formatOutput(deadline) + ")");
    }

    @Override
    public void announceTaskAdd(){
        super.announceTaskAdd();
        super.printTask(this.toString());
    }

    @Override
    public String toFileFormat() {
        return ("D | " + super.toFileFormat() + " | /by " + getDeadline());
    }

    public void announceTaskDelete() {
        super.announceTaskDelete();
        super.printTask(this.toString());
    }

}