package RC.command;

import RC.RCException;
import RC.TaskList;

public abstract class RCCommand {
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String DELETE_COMMAND = "delete";
    private static final String EXIT_COMMAND = "bye";

    public static RCCommand getCommand(String input) throws RCException {
        String[] split = input.split(" ", 2);
        String command = split[0].toLowerCase();
        String restOfInput = split.length > 1 ? split[1] : "";

        switch (command) {
        case TODO_COMMAND:
            return new TodoCommand(restOfInput);
        case DEADLINE_COMMAND:
            return new DeadlineCommand(restOfInput);
        case EVENT_COMMAND:
            return new EventCommand(restOfInput);
        case LIST_COMMAND:
            return new List();
        case MARK_COMMAND:
            return new Mark(restOfInput);
        case UNMARK_COMMAND:
            return new Unmark(restOfInput);
        case DELETE_COMMAND:
            return new Delete(restOfInput);
        case EXIT_COMMAND:
            return new Exit();
        default:
            return new Invalid();
        }
    }

    public abstract void execute(TaskList taskList) throws RCException;
}
