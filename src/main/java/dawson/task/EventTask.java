package dawson.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import dawson.exception.DawsonException;
import dawson.parser.Parser;
import dawson.ui.Messages;

/**
 * Represents an Event which has additional start and end times
 */
public class EventTask extends Task {

    protected String start;
    protected String end;

    protected LocalDateTime startDateTime; // Can be null
    protected LocalDateTime endDateTime; // Can be null

    /**
     * Create an EventTask with a description, start date and time, and end date and time.
     * Parse start and end string into LocalDateTime objects, null if unable to parse
     *
     * @param description The description or details of the event task.
     * @param start       The start date and time of the event in string format.
     * @param end         The end date and time of the event in string format.
     */
	public EventTask(String description, String start, String end) {
		super(description);
        this.start = start;
        this.end = end;

        this.startDateTime = Parser.parseDateTime(start, true);
        this.endDateTime = Parser.parseDateTime(end, false);
	}

    /**
     * Checks if an EventTask contains a specific query date within its start and end date range.
     *
     * @param queryDate The date to be checked for existence within the task's date range.
     * @return `true` if the task contains the query date within its date range, `false` otherwise.
     */
    public boolean containsQueryDate(LocalDate queryDate) {
        if (startDateTime == null && endDateTime == null) {
            return false; // no date range
        }

        // Only start date exists, check if queryDate matches start date
        if (startDateTime != null && endDateTime == null) {
            LocalDate startDate = startDateTime.toLocalDate();
            return startDate.equals(queryDate);
        } 
        
        // Only end date exists, check if queryDate matches end date
        if (startDateTime == null && endDateTime != null) {
            LocalDate endDate = endDateTime.toLocalDate();
            return endDate.equals(queryDate);
        } 
        
        // Both startDateTime and endDateTime exist; check if the query date is WITHIN the date range
        LocalDate startDate = startDateTime.toLocalDate();
        LocalDate endDate = endDateTime.toLocalDate();
        return isWithinDateRange(queryDate, startDate, endDate);
    }

    /**
     * Checks if a given date falls within a specified date range.
     *
     * @param query The date to be checked.
     * @param start The start date of the date range (inclusive).
     * @param end   The end date of the date range (inclusive).
     * @return `true` if the query date is within the specified date range, `false` otherwise.
     */
    private boolean isWithinDateRange(LocalDate query, LocalDate start, LocalDate end) {
        boolean notBeforeStart = !query.isBefore(start);
        boolean notAfterEnd = !query.isAfter(end);
        return notBeforeStart && notAfterEnd;
    }

    /**
     * Decodes an encoded string into an EventTask object. 
     * Extract description, isDone status, start time, and end time fields
     *
     * @param encodedString The encoded string to be decoded into an EventTask.
     * @return An EventTask object representing the decoded event task.
     * @throws DawsonException If there is an issue decoding the string or missing fields in the encodedString.
     */
    public static Task decodeEvent(String encodedString) throws DawsonException {
        final int EVENT_FIELDS_NO = 5;
        final int ISDONE_INDEX = 1;
        final int DESC_INDEX = 2;
        final int START_INDEX = 3;
        final int END_INDEX = 4;

        String[] eventSplit = encodedString.split(Parser.TASK_DELIMITER, EVENT_FIELDS_NO);
        if (eventSplit.length < EVENT_FIELDS_NO) {
            throw new DawsonException(Messages.MESSAGE_PARSE_TASK_ERROR);
        }

        String desc = eventSplit[DESC_INDEX].trim();
        String isDone = eventSplit[ISDONE_INDEX].trim();
        String start = eventSplit[START_INDEX].trim();
        String end = eventSplit[END_INDEX].trim();

        EventTask event = new EventTask(desc, start, end);
        if (isDone.equals("1")) event.markAsDone();
        return event;
    }

    @Override
    public String toString() {
        String startString = startDateTime == null ? start : Parser.userDateTimeFormat.format(startDateTime);
        String endString = endDateTime == null ? end : Parser.userDateTimeFormat.format(endDateTime);
        return String.format("[E]%s (from: %s, to: %s)", super.toString(), startString, endString);
    }

    @Override
    public String encode() {
        String isDoneString = isDone ? "1" : "0";
        return String.format("E | %s | %s | %s | %s", isDoneString, description, start, end);
    }
    
}