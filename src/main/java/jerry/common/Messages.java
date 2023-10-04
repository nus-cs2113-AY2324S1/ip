package jerry.common;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_GOODBYE = "Good bye!";
    public static final String MESSAGE_INIT_FAILED = "Failed to initialise Jerry application. Exiting...";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The taks index provided is invalid";
    public static final String MESSAGE_TASK_NOT_IN_TASKLIST = "Task could not be found in task list";
    public static final String MESSAGE_TASK_LISTED_OVERVIEW = "%1$d tasks listed!";
    public static final String MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE = "Launch command format: "
            + "<command> [<index>] [<content>]";
    public static final String MESSAGE_WELCOME = "Welcome to your TaskList!";
}
