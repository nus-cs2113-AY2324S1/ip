package taskmanagement;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Event class in Zran application.
 * It represents a task of class 'Event' which consists of two additional variables, 'to' and 'from'.
 * Extends the base Task class.
 */
public class Event extends Task {
    protected String from;
    protected String to;
    
     /**
     * Constructs an instance of 'Event' with the given description and deadline.
     *
     * @param description The description of the event task.
     * @param from        The start date of the event.
     * @param to          The end date of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        taskType = "E";
    }

    /**
     * Constructs an instance of 'Event' with the given description and deadline.
     *
     * @param description The description of the event task.
     * @param from        The start date of the event.
     * @param to          The end date of the event.
     * @param isDone      The completion status of the task.
     */
    public Event(String description, String from, String to, Boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        taskType = "E";
        this.isDone = isDone;
    }
    @Override
    public String toString() {
        LocalDate displayedFrom;
        LocalDate displayedTo;
        try {
            displayedFrom = LocalDate.parse(from);
            displayedTo = LocalDate.parse(to);
            return "[" + taskType + "]" + "[" + getStatusIcon() + "] " + description + " (from: " + displayedFrom.format(formatter) + " to: " + displayedTo.format(formatter) + ")";
        } catch (DateTimeException e) {
            return "[" + taskType + "]" + "[" + getStatusIcon() + "] " + description + " (from: " + from + " to: " + to + ")";
        }
    }
    public String getTo(){ return to; }
    public String getFrom(){ return from; }
}
