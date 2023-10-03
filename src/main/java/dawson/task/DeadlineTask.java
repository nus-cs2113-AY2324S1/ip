package dawson.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import dawson.exception.DawsonException;
import dawson.parser.Parser;
import dawson.ui.Messages;

/**
 * Represents a Deadline which has an additional due date field (called "by")
 */
public class DeadlineTask extends Task {

    protected String by;
    protected LocalDateTime byDateTime; // Can be null

    /**
     * Create a DeadlineTask with a description and a due date and time.
     * Parse due date string into LocalDateTime objects, null if unable to parse.
     *
     * @param description The description or details of the deadline task.
     * @param by          The due date and time of the task in string format.
     */
	public DeadlineTask(String description, String by) {
		super(description);
        this.by = by;

       byDateTime = Parser.parseDateTime(by, false);
	}

    /**
     * Checks if the DeadlineTask has a specific query date matching its due date.
     *
     * @param queryDate The date to be checked for equality with the task's due date.
     * @return `true` if the task has a due date that matches the query date; otherwise, `false`.
     */
    public boolean containsQueryDate(LocalDate queryDate) {
        if (byDateTime == null) {
            return false;
        }

        LocalDate deadlineDate = byDateTime.toLocalDate();
        return deadlineDate.equals(queryDate);
    }

    /**
     * Decodes an encoded string into a DeadlineTask object. 
     * Extract description, isDone and due date fields
     *
     * @param encodedString The encoded string to be decoded into a DeadlineTask.
     * @return A DeadlineTask object representing the decoded task.
     * @throws DawsonException If there is an issue decoding the string or missing fields in the encodedString.
     */
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

    @Override
    public String toString() {
        String byString = byDateTime == null ? by : Parser.userDateTimeFormat.format(byDateTime);
        return String.format("[D]%s (by: %s)", super.toString(), byString);
    }

    @Override
    public String encode() {
        String isDoneString = isDone ? "1" : "0";
        return String.format("D | %s | %s | %s", isDoneString, description, by);
    }
    
}
