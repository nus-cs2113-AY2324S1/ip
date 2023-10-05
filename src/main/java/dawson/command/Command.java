package dawson.command;

import dawson.exception.DawsonException;
import dawson.task.TaskList;

/**
 * Base abstract class for all command classes in the Dawson application.
 *
 * <p> Each concrete command class will extend this class and 
 * implement the execute method to perform its respective functionality.
 */
public abstract class Command {
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";

    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";

    public static final String LIST_COMMAND = "list";
    public static final String FIND_COMMAND = "find";
    public static final String DATE_COMMAND = "date";
    public static final String DELETE_COMMAND = "delete";
    public static final String EXIT_COMMAND = "bye";

    /**
     * Executes the specific command, manipulating the provided TaskList as needed.
     *
     * @param list The TaskList to be operated on by the command.
     * @return A CommandResult containing messages and information about the execution of the command.
     * @throws DawsonException If an error occurs during the execution of the command.
     */
    public abstract CommandResult execute(TaskList list) throws DawsonException;
}
