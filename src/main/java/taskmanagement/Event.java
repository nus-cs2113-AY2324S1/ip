package taskmanagement;

import java.time.LocalDate;

/**
 * Event class in Zran application.
 * It represents a task of class 'Event' which consists of two additional variables, 'to' and 'from'.
 * Extends the base Task class.
 */
public class Event extends Task {
//    protected String from;
//    protected String to;
    protected LocalDate from;
    protected LocalDate to;
    
     /**
     * Constructs an instance of 'Event' with the given description and deadline.
     *
     * @param description The description of the event task.
     * @param from        The start date of the event.
     * @param to          The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
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
    public Event(String description, LocalDate from, LocalDate to, Boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        taskType = "E";
        this.isDone = isDone;
    }
    @Override
    public String toString() {
        return "[" + taskType + "]" + "[" + getStatusIcon() + "] " + description + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
    public LocalDate getTo(){ return to; }
    public LocalDate getFrom(){ return from; }
}
