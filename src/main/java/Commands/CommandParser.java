package commands;

import tasks.Tasklist;
import exceptions.UnknownCommandException;

/**
 * Represents a parser that parses the user's input and returns the appropriate command.
 */
public class CommandParser {
    private static final String ADD_TODO_COMMAND = "todo";
    private static final String ADD_DEADLINE_COMMAND = "deadline";
    private static final String ADD_EVENT_COMMAND = "event";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";
    private static final String FAREWELL_COMMAND = "bye";

    /**
     * Parses the user's input and returns the appropriate command.
     * @param commandString the user's input.
     * @param tasklist the tasklist that the command will be executed on.
     * @return the appropriate command.
     * @throws UnknownCommandException if the user's input is not recognised.
     */
    public static Command parse(String commandString, Tasklist tasklist) throws UnknownCommandException {
        String[] commandTokens = commandString.split(" ");
        String command = commandTokens[0];

        switch (command) {
        case ADD_TODO_COMMAND:
            return new AddTodo(commandString, tasklist);
        case ADD_DEADLINE_COMMAND:
            return new AddDeadline(commandString, tasklist);
        case ADD_EVENT_COMMAND:
            return new AddEvent(commandString, tasklist);
        case LIST_COMMAND:
            return new ListTasks(tasklist);
        case MARK_COMMAND:
            return new MarkTask(commandString, tasklist);
        case UNMARK_COMMAND:
            return new UnmarkTask(commandString, tasklist);
        case DELETE_COMMAND:
            return new DeleteTask(commandString, tasklist);
        case FIND_COMMAND:
            return new FindTask(commandString, tasklist);
        case FAREWELL_COMMAND:
            return new Farewell(tasklist);
        default:
            throw new UnknownCommandException();
        }
    }
}
