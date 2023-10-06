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

    @Override
    public String toString() {
        String typeOfTask = "[D]";
        String statusOfTask = "[" + super.getStatusIcon() + "] ";
        String task = super.getDescription() + " (by: " + deadline.format(DateTimeParser.getFormatter()) + ")";
        return typeOfTask + statusOfTask + task;
    }

    /**
     * Encodes the deadline task into a string to be stored in the text file
     *
     * @return string in the format to be stored in text file
     */
    @Override
    public String encode() {
        String encodeDeadline = getDeadline().format(DateTimeParser.getFormatter());
        return String.format("Deadline | %s | %s", super.encode(), encodeDeadline);
    }
}