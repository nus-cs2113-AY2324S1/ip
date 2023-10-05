package dawson.task;

import dawson.exception.DawsonException;
import dawson.parser.Parser;
import dawson.ui.Messages;

/**
 * Represents a Todo task 
 */
public class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String encode() {
        String isDoneString = isDone ? "1" : "0";
        return String.format("T | %s | %s", isDoneString, description);
    }

    /**
     * Decodes an encoded string into a TodoTask object.
     * Extract description and isDone status fields
     *
     * @param encodedString The encoded string to be decoded into a TodoTask.
     * @return A TodoTask object representing the decoded task.
     * @throws DawsonException If there is an issue decoding the string or missing fields in the encodedString.
     */
    public static Task decodeTodo(String encodedString) throws DawsonException {
        final int TODO_FIELDS_NO = 3;
        final int DESC_INDEX = 2;
        final int ISDONE_INDEX = 1;

        String[] todoSplit = encodedString.split(Parser.TASK_DELIMITER, TODO_FIELDS_NO);
        if (todoSplit.length < TODO_FIELDS_NO) {
            throw new DawsonException(Messages.MESSAGE_PARSE_TASK_ERROR);
        }

        String desc = todoSplit[DESC_INDEX].trim();
        String isDone = todoSplit[ISDONE_INDEX].trim();

        TodoTask todo = new TodoTask(desc);
        if (isDone.equals("1")) todo.markAsDone();
        return todo;
    }

}