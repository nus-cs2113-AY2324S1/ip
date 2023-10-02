package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task in the Barbie-themed task manager.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Initializes a new Event task with the given description, start date and end date.
     *
     * @param description The description of the Event task.
     * @param from The start date and time of the Event.
     * @param to The end date and time of the Event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));;
        this.to = to.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));;
    }

    /**
     * Gets the formatted start date and time of the Event.
     *
     * @return The formatted start date and time.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Gets the formatted end date and time of the Event.
     *
     * @return The formatted end date and time.
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the start date and time of the Event.
     *
     * @param from The new start date and time.
     */
    public void setFrom(LocalDateTime from) {
        this.from = from.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
    }

    /**
     * Sets the end date and time of the Event.
     *
     * @param to The new end date and time.
     */
    public void setTo(LocalDateTime to) {
        this.to = to.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
    }

    /**
     * Returns a new string representation of the Event task, including its status, description, start date and end date.
     *
     * @return A string representing the Event task, in the format: "[E] [X] Description (from: StartDate to: EndDate)".
     */
    @Override
    public String toString() {
        return "\t[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
