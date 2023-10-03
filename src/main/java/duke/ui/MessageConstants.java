package duke.ui;

/**
 * Contains all the messages that Duke will display to the user.
 */

public class MessageConstants {
    public static final String LINE = "____________________________________________________________";
    public static final String LOGO  = 
                " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String MESSAGE_WELCOME = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    
    public static final String MESSAGE_LIST = "Here are the tasks in your list: ";
    public static final String MESSAGE_DELETE = "Noted. I've removed this task: ";
    public static final String MESSAGE_ADD = "Got it. I've added this task: ";
    public static final String MESSAGE_FIND = "Here are the matching tasks in your list: ";
    public static final String MESSAGE_MARK = "Nice! I've marked this task as done: ";
    public static final String MESSAGE_UNMARK = "Nice! I've unmarked this task as done: ";
    
    public static final String MESSAGE_ERROR_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that command means :-(";
    public static final String MESSAGE_ERROR_INVALID_TASK_NUMBER = "☹ OOPS!!! The task number is invalid.";
    public static final String MESSAGE_ERROR_EMPTY_DESCRIPTION = "☹ OOPS!!! The description of a task cannot be empty.";
    public static final String MESSAGE_ERROR_FROM_TIME_MISSING = "☹ OOPS!!! The /from time of an event cannot be empty.";
    public static final String MESSAGE_ERROR_TO_TIME_MISSING = "☹ OOPS!!! The /to time of an event cannot be empty.";
    public static final String MESSAGE_ERROR_TO_BEFORE_FROM = "☹ OOPS!!! The /from time of an event must be typed before the /to time.";
    public static final String MESSAGE_ERROR_DEADLINE_BY = "☹ OOPS!!! The /by time of a deadline cannot be empty.";
    public static final String MESSAGE_ERROR_FIND_MISSING = "☹ OOPS!!! The word you are searching for cannot be empty.";
    public static final String MESSAGE_ERROR_FILE_CREATION = "☹ OOPS!!! Error creating file.";
    public static final String MESSAGE_ERROR_FILE_READING = "☹ OOPS!!! Error reading from file.";
    public static final String MESSAGE_ERROR_FILE_WRITING = "☹ OOPS!!! Error writing to file.";

}
