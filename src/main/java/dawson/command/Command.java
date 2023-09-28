package dawson.command;

import dawson.TaskList;
import dawson.exception.DawsonException;

public abstract class Command {
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

    private static final String LIST_COMMAND = "list";
    private static final String DELETE_COMMAND = "delete";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String EXIT_COMMAND = "bye";

    public static Command getCommand(String input, TaskList taskList) {
        String[] split = input.split("\\s+", 2);
        String commandString = split[0].toLowerCase(); // First word is command
        String payload = split.length > 1 ? split[1] : ""; // Remaining input text

        switch (commandString) {
            case TODO_COMMAND:
                return new TodoCommand(payload, taskList);
            case DEADLINE_COMMAND:
                return new DeadlineCommand(payload, taskList);
            case EVENT_COMMAND:
                return new EventCommand(payload, taskList);
            case LIST_COMMAND:
                return new ListCommand(taskList);
            case DELETE_COMMAND:
                return new DeleteCommand(payload, taskList);
            case MARK_COMMAND:
                return new MarkCommand(payload, taskList);
            case UNMARK_COMMAND:
                return new UnmarkCommand(payload, taskList);
            case EXIT_COMMAND:
                return new ExitCommand();
            default:
                return new InvalidCommand();
        }
    };

    public abstract void execute() throws DawsonException;
}
