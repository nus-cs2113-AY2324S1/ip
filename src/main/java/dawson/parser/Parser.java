package dawson.parser;

import dawson.command.Command;
import dawson.command.DeadlineCommand;
import dawson.command.DeleteCommand;
import dawson.command.EventCommand;
import dawson.command.ExitCommand;
import dawson.command.InvalidCommand;
import dawson.command.ListCommand;
import dawson.command.MarkCommand;
import dawson.command.TodoCommand;
import dawson.command.UnmarkCommand;
import dawson.exception.DawsonException;
import dawson.task.*;

public class Parser {

    public static Command parseCommand(String input) {
        String[] split = input.split("\\s+", 2); // split the input into command and arguments
        String commandString = split[0].toLowerCase(); // First word is command
        String payload = split.length > 1 ? split[1] : ""; // Remaining input text

        switch (commandString) {
            case Command.TODO_COMMAND:
                return new TodoCommand(payload);
            case Command.DEADLINE_COMMAND:
                return new DeadlineCommand(payload);
            case Command.EVENT_COMMAND:
                return new EventCommand(payload);
            case Command.LIST_COMMAND:
                return new ListCommand();
            case Command.DELETE_COMMAND:
                return new DeleteCommand(payload);
            case Command.MARK_COMMAND:
                return new MarkCommand(payload);
            case Command.UNMARK_COMMAND:
                return new UnmarkCommand(payload);
            case Command.EXIT_COMMAND:
                return new ExitCommand();
            default:
                return new InvalidCommand();
        }
    };

    public static Task parseTask(String encodedTaskString) throws DawsonException {
        final String DELIMITER = "\\|";

        final int TODO_FIELDS_NO = 3;
        final int DEADLINE_FIELDS_NO = 4;
        final int EVENT_FIELDS_NO = 5;

        final int TYPE_INDEX = 0;
        final int ISDONE_INDEX = 1;
        final int DESC_INDEX = 2;
        final int BY_INDEX = 3;
        final int START_INDEX = 3;
        final int END_INDEX = 4;

        String[] split = encodedTaskString.split(DELIMITER, 2);
        String taskType = split[TYPE_INDEX].trim(); // Get first letter

        switch (taskType) {
            case "T":
                String[] todoSplit = encodedTaskString.split(DELIMITER, TODO_FIELDS_NO);
                if (todoSplit.length >= TODO_FIELDS_NO) {
                    String desc = todoSplit[DESC_INDEX].trim();
                    String isDone = todoSplit[ISDONE_INDEX].trim();

                    TodoTask todo = new TodoTask(desc);
                    if (isDone.equals("1")) todo.markAsDone();
                    return todo;
                }
                break;

            case "D":
                String[] deadlineSplit = encodedTaskString.split(DELIMITER, DEADLINE_FIELDS_NO);
                if (deadlineSplit.length >= DEADLINE_FIELDS_NO) {
                    String desc = deadlineSplit[DESC_INDEX].trim();
                    String isDone = deadlineSplit[ISDONE_INDEX].trim();
                    String by = deadlineSplit[BY_INDEX].trim();

                    DeadlineTask deadline = new DeadlineTask(desc, by);
                    if (isDone.equals("1")) deadline.markAsDone();
                    return deadline;
                }
                break;

            case "E":
                String[] eventSplit = encodedTaskString.split(DELIMITER, EVENT_FIELDS_NO);
                if (eventSplit.length >= EVENT_FIELDS_NO) {
                    String desc = eventSplit[DESC_INDEX].trim();
                    String isDone = eventSplit[ISDONE_INDEX].trim();
                    String start = eventSplit[START_INDEX].trim();
                    String end = eventSplit[END_INDEX].trim();

                    EventTask event = new EventTask(desc, start, end);
                    if (isDone.equals("1")) event.markAsDone();
                    return event;
                }
                break;
        }
        throw new DawsonException("Error parsing tasks from storage!");
    }

}
