package duke;

/**
 * Represents an event task which is a task with a start date and an end date.
 */
public class Event extends Task {

    private String startDate;
    private String endDate;

    /**
     * Constructor for Event class.
     * @param description Description of the event.
     * @param startDate Start date of the event.
     * @param endDate End date of the event.
     */
    public Event(String description, String startDate, String endDate){
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    /**
     * Returns the single line representation of the event which is used by the UI.
     * @return string representation of the event.
     */
    public String toString(){
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }

    @Override
    /**
     * Returns the single line representation of the event which is used for the file.
     * @return string representation of the event.
     */
    public String toFileString(){
        return ("E | " + super.toFileString() + " | " + startDate + " | " + endDate);
    }
}
