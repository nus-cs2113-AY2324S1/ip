package elgin.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static elgin.parser.Parser.DATETIME_FORMATTER;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy hh.mma");

    /**
     * Constructor for Event.
     *
     * @param description Description of Event.
     * @param from Starting date time of Event.
     * @param to Ending date time of Event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        type = "E";
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor for Event.
     * Called when loading Event from saved file.
     *
     * @param description Description of Event.
     * @param from Starting date time of Event.
     * @param to Ending date time of Event.
     * @param isDone True if the Event is completed, else False.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description);
        type = "E";
        this.from = from;
        this.to = to;
        setIsDone(isDone);
    }

    /**
     * Gets Event starting date time in 24 hours clock format.
     *
     * @return LocalDateTime of Event start
     */
    public String getFrom() {
        return from.format(DATETIME_FORMATTER);
    }

    /**
     * Gets Event starting date time in 12 hours clock format.
     *
     * @return LocalDateTime of Event start
     */
    public String getFromAs12HourClock() {
        return from.format(FORMATTER);
    }

    /**
     * Gets Event ending date time in 24 hours clock format.
     *
     * @return LocalDateTime of Event end
     */
    public String getTo() {
        return to.format(DATETIME_FORMATTER);
    }

    /**
     * Gets Event ending date time in 12 hours clock format.
     *
     * @return LocalDateTime of Event end
     */
    public String getToAs12HourClock() {
        return to.format(FORMATTER);
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + getFromAs12HourClock() + ", to: " + getToAs12HourClock() + ")";
    }
}
