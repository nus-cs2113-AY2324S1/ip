package chat0pt.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private static final DateTimeFormatter ORIGINALFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private LocalDateTime by;


    /**
     * Constructor for deadline
     * @param task Name of the task
     * @param By Deadline of the task
     */

    public Deadline(String tasks, LocalDateTime By) {
        super(tasks);
        this.by = By;
    }

    public String getBy() {
        return this.by.format(DATEFORMAT);
    }

    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + getBy() + ")";
    }

    @Override
    public String toFile() {
        return "D#" + super.toFile() + "#" + this.by.format(ORIGINALFORMAT);
    }

}
