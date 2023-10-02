package dawson.task;

import dawson.exception.DawsonException;
import dawson.parser.Parser;
import dawson.ui.Messages;

public class DeadlineTask extends Task {

    protected String by;

	public DeadlineTask(String description, String by) {
		super(description);
        this.by = by;
	}

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }

    @Override
    public String encode() {
        String isDoneString = isDone ? "1" : "0";
        return String.format("D | %s | %s | %s", isDoneString, description, by);
    }

    public static Task decodeDeadline(String encodedString) throws DawsonException {
        final int DEADLINE_FIELDS_NO = 4;
        final int ISDONE_INDEX = 1;
        final int DESC_INDEX = 2;
        final int BY_INDEX = 3;

        String[] deadlineSplit = encodedString.split(Parser.TASK_DELIMITER, DEADLINE_FIELDS_NO);
        if (deadlineSplit.length < DEADLINE_FIELDS_NO) {
            throw new DawsonException(Messages.MESSAGE_PARSE_TASK_ERROR);
        }

        String desc = deadlineSplit[DESC_INDEX].trim();
        String isDone = deadlineSplit[ISDONE_INDEX].trim();
        String by = deadlineSplit[BY_INDEX].trim();

        DeadlineTask deadline = new DeadlineTask(desc, by);
        if (isDone.equals("1")) deadline.markAsDone();
        return deadline;
    }
    
}
