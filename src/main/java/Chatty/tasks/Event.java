/**
 * The Event class represents a task with an event date.
 */
package Chatty.tasks;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;


    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.to = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * gets 'from' date
     * @return from date
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * gets 'to' date
     * @return to date
     */
    public LocalDate getTo() {
        return to;
    }
    /**
     * Gets the description for the deadline task
     */
    @Override
    public String getDescription() {
        String formattedFrom = from.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        String formattedTo = to.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return "[E][" + getStatusIcon() + "] " + super.getDescription() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }

    @Override
    public String saveFormat() {
        return "E | " + (getIsDone() ? "1" : "0") + " | " + description + " | " + from + " to " + to;
    }
}
