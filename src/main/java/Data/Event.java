package Data;


import java.time.LocalDate;

/**
 * Class to represent an event task
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for Event task
     * @param description Description of the task
     * @param from Start date of the event
     * @param to End date of the event
     */
    public Event (String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the start date
     * @return String representation of the start date
     */
    public String getFromString() {
        return this.from.format(Task.DATE_FORMAT);
    }

    public String getFromStringToSave() {
        return this.from.toString();
    }

    /**
     * Returns the string representation of the end date
     * @return String representation of the end date
     */
    public String getToString() {
        return this.to.format(Task.DATE_FORMAT);
    }

    public String getToStringToSave() {
        return this.to.toString();
    }

    /**
     * Returns the string representation of the task
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getFromString() + " to: " + this.getToString() + ")";
    }
}
