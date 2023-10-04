package Duchess.TextObjects;

/**
 * Defines constants used in commands and
 * other miscellaneous contexts.
 */


public class Constants {
    /** User command for exiting. */
    public static final String endCommand = "bye";
    /** User command for displaying. */
    public static final String listCommand = "list";
    /** User command for marking. */
    public static final String markCommand = "mark";
    /** User command for unmarking. */
    public static final String unmarkCommand = "unmark";
    /** User command for adding a to-do task. */
    public static final String todoCommand = "todo";
    /** User command for adding a deadline task. */
    public static final String deadlineCommand = "deadline";
    /** User command for adding an event task. */
    public static final String eventCommand = "event";
    /** User command for specifying deadline in corresponding task. */
    public static final String deadlineTime = "/by";
    /** User command for specifying start time in event task. */
    public static final String eventStartTime = "/from";
    /** User command for specifying end time in event task. */
    public static final String eventEndTime = "/to";
    /** User command for deleting a task. */
    public static final String deleteCommand = "delete";
    /** User command for finding a task. */
    public static final String findCommand = "find";
    /** Filepath specification string. */
    public static final String taskFilePath = "./src/main/java/data/tasks.txt";
}
