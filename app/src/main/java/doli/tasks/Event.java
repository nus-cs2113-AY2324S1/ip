package doli.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h3>Event class</h3>
 * The Event class extends the Task class and is specifically
 * made to handle tasks of type event, meaning that they have
 * both a starting and ending date in addition to a detailed description.
 *
 * @author pappalardodaniel
 * @version 1.0
 * @since 2023-11-03
 */
public class Event extends Task {
    protected LocalDate startTime;
    protected LocalDate endTime;
    protected final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructs an object of type Event and calls its superclass'constructor.
     * It contains a description of the event and two variables for the timing.
     * @param description of type String referring to details about the event
     * @param startTimeInput of type String ("yyyy-MM-dd") to be parsed into type LocalDate for further use
     * @param endTimeInput of type String ("yyyy-MM-dd") to be parsed into type LocalDate for further use
     */
    public Event(String description, String startTimeInput, String endTimeInput) {
        super(description);
        try {
            this.startTime = LocalDate.parse(startTimeInput);
            this.endTime = LocalDate.parse(endTimeInput);
        } catch(DateTimeException e) {
            System.out.println("Could not parse the dates.");
        }
    }

    /**
     * Returns the starting date of the event
     * @return start date of type LocalDate
     */
    public LocalDate getStartTime() {
        return startTime;
    }

    /**
     * Returns the ending date of the event
     * @return end date of type LocalDate
     */
    public LocalDate getEndTime() {
        return endTime;
    }

    /**
     * Returns the interval between the starting and ending dates of the event
     * @return time interval of types LocalDate
     */
    public String getTime() {
        String timeInterval = startTime + " - " + endTime;
        return timeInterval;
    }

    /**
     * Sets the starting date of the event
     * @return newly set start date of type LocalDate
     */
    public void setStartTime(String startTime) {
        try {
            this.startTime = LocalDate.parse(startTime);
        } catch(DateTimeException e) {
            System.out.println("Could not parse the start-time.");
        }
    }

    /**
     * Sets the ending date of the event
     * @return newly set end date of type LocalDate
     */
    public void setEndTime(String endTime) {
        try {
            this.endTime = LocalDate.parse(endTime);
        } catch(DateTimeException e) {
            System.out.println("Could not parse the start-time.");
        }
    }

    /**
     * Sets both the starting and ending dates of the event
     * @return newly set start and end dates of type LocalDate
     */
    public void setTime(String startTime, String endTime) {
        try {
            this.startTime = LocalDate.parse(startTime);
            this.endTime = LocalDate.parse(endTime);
        } catch(DateTimeException e) {
            System.out.println("Could not parse the dates.");
        }
    }
    /**
     * Changes the boolean variable isDone of the task
     * @param done of type boolean
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }
    /**
     * Returns whether an event task was completed/is over or not
     * @return boolean variable explaining whether the event is over/has been completed or not
     */
    public boolean isDone() {
        return isDone;
    }
    /**
     * Overrides the superclass' method .toString() allowing for
     * a tailored use incorporating details of the task such as its type and whether it is done or not.
     * @return a String summarising the event's details
     */
    @Override
    public String toString() {
        String summary = String.format("[E] %s (%s, %s)", super.toString(), // E stands for Event
                "from: " + DATE_FORMATTER.format(startTime),
                "to: " + DATE_FORMATTER.format(endTime));
        return summary;
    }
}
