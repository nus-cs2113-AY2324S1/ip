package alice.tasks;

import java.time.LocalDateTime;
import alice.parser.DateTimeParser;

public class Deadline extends Task {
    private LocalDateTime deadline;
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String typeOfTask = "[D]";
        String statusOfTask = "[" + super.getStatusIcon() + "] ";
        String task = super.getDescription() + " (by: " + deadline.format(DateTimeParser.getFormatter()) + ")";
        return typeOfTask + statusOfTask + task;
    }

    @Override
    public String encode() {
        String encodeDeadline = getDeadline().format(DateTimeParser.getFormatter());
        return String.format("Deadline | %s | %s", super.encode(), encodeDeadline);
    }
}