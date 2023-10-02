package dawson.command;

import dawson.exception.DawsonException;
import dawson.task.TaskList;

public abstract class Command {
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";

    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";

    public static final String LIST_COMMAND = "list";
    public static final String DELETE_COMMAND = "delete";
    public static final String EXIT_COMMAND = "bye";

    public abstract CommandResult execute(TaskList list) throws DawsonException;
}
