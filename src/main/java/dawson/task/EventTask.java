package dawson.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import dawson.exception.DawsonException;
import dawson.parser.Parser;
import dawson.ui.Messages;

public class EventTask extends Task {

    protected String start;
    protected String end;

    protected LocalDateTime startDateTime; // Can be null
    protected LocalDateTime endDateTime; // Can be null

	public EventTask(String description, String start, String end) {
		super(description);
        this.start = start;
        this.end = end;

        this.startDateTime = Parser.parseDateTime(start, true);
        this.endDateTime = Parser.parseDateTime(end, false);
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

    public boolean containsQueryDate(LocalDate queryDate) {
        if (startDateTime == null && endDateTime == null) {
            return false;
        }

        if (startDateTime != null && endDateTime == null) {
            LocalDate startDate = startDateTime.toLocalDate();
            return startDate.equals(queryDate);

        } else if (startDateTime == null && endDateTime != null) {
            LocalDate endDate = endDateTime.toLocalDate();
            return endDate.equals(queryDate);

        } else { // Both start and end DateTime exist
            LocalDate startDate = startDateTime.toLocalDate();
            LocalDate endDate = endDateTime.toLocalDate();
            return isWithinDateRange(queryDate, startDate, endDate);
        }
    }

    private boolean isWithinDateRange(LocalDate query, LocalDate start, LocalDate end) {
        boolean notBeforeStart = !query.isBefore(start);
        boolean notAfterEnd = !query.isAfter(end);
        return notBeforeStart && notAfterEnd;
    }

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
    
}