package dawson.task;

import java.time.LocalDateTime;

import dawson.exception.DawsonException;
import dawson.parser.Parser;
import dawson.ui.Messages;

public class EventTask extends Task {

    protected String start;
    protected String end;

    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

	public EventTask(String description, String start, String end) {
		super(description);
        this.start = start;
        this.end = end;

        this.startDateTime = parseDateTime(start, true);
        this.endDateTime = parseDateTime(end, false);
	}

    @Override
    public String toString() {
        String startString = startDateTime == null ? start : showDateTimeFormat.format(startDateTime);
        String endString = endDateTime == null ? end : showDateTimeFormat.format(endDateTime);
        return String.format("[E]%s (from: %s, to: %s)", super.toString(), startString, endString);
    }

    @Override
    public String encode() {
        String isDoneString = isDone ? "1" : "0";
        return String.format("E | %s | %s | %s | %s", isDoneString, description, start, end);
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