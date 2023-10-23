package elvis.task;

import elvis.operation.DateTimeHandler;

import java.time.LocalDateTime;

/**
 * Represents an Event task that extends the Task class.
 * An Event task has a start and end date-time.
 */
public class Event extends Task {
    /**
     * The type of the task, represented as a character.
     */
    private final char taskType = 'E';

    /**
     * The start date-time of the event.
     */
    private LocalDateTime startDateTime;

    /**
     * The end date-time of the event.
     */
    private LocalDateTime endDateTime;

    /**
     * Constructs an Event object.
     *
     * @param description The description of the event.
     * @param isDoneFromFile The status of the task, read from the file.
     * @param fromWhen The start date-time of the event.
     * @param toWhen The end date-time of the event.
     */
    public Event(String description, int isDoneFromFile, String fromWhen, String toWhen) {
        super(description, isDoneFromFile);
        this.startDateTime = DateTimeHandler.dateTimeParser(fromWhen);
        this.endDateTime = DateTimeHandler.dateTimeParser(toWhen);
    }

    /**
     * Retrieves the type of the task.
     *
     * @return The type of the task as a character.
     */
    @Override
    public char getTaskType() {
        return taskType;
    }

    /**
     * Retrieves the start date-time of the event.
     *
     * @return The start date-time of the event.
     */
    @Override
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Retrieves the start date-time of the event.
     *
     * @return The start date-time of the event.
     */
    @Override
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
}
