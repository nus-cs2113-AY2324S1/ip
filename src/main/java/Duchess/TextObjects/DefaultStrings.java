package Duchess.TextObjects;


/** Class to define commonly used or long strings for other classes. */
public class DefaultStrings {
    /** String that contains line dividers. */
    public static final String splittingLine = "\t____________________________________________________________\n";
    /** Startup string */
    public static final String logo = splittingLine +
            "\t Hewwo! I'm Duchess-sama\n" +
            "\t What can I do for youwu?\n" +
            splittingLine;
    /** String that starts the numTasks function. */
    public static final String numTasksStringStart = "\t You have ";
    /** String that ends the numTasks function. */
    public static final String numTasksStringEnd = " tasks to do, haiyaku!!!";
    /** String for task deletion feedback. */
    public static final String deletedString = splittingLine + 
            "\t I've removed this task for you, goshujin-sama: ";
    /** String for invalid task error handling. */
    public static final String invalidTaskString = splittingLine + 
            "\t That task doesn't exist, goshujin-sama.\n" + splittingLine;
    /** String for exiting the program. */
    public static final String endString = splittingLine + 
            "\t Bye. I know you don\'t want me. Never come back.\n" + splittingLine;
    /** String after adding a task. */
    public static final String addedString = splittingLine + "\t At your command, goshujin-sama: ";
    /** String after marking a task. */
    public static final String markedString = splittingLine + "\t Congwats on finishing za tawsk: ";
    /** String after unmarking a task. */
    public static final String unmarkedString = splittingLine + "\t WHY HAVE YOU FORSAKEN ME\n" +
            "\t I mean, I've unmarked this task for you, goshujin-sama: ";
    /** String for unrecognised command error handling. */
    public static final String unrecognisedString = splittingLine + 
            "\t I don\'t understand what you\'re saying, goshujin-sama.\n" + splittingLine;
    /** String for invalid todo error handling. */
    public static final String emptyToDoString = splittingLine + 
            "\t You can\'t add an empty task, goshujin-sama.\n" + splittingLine;
    /** String for invalid deadline error handling. */
    public static final String emptyDeadlineString = splittingLine + 
            "\t You can\'t add an empty deadline, goshujin-sama.\n" + splittingLine;
    /** String for invalid event error handling. */
    public static final String emptyEventString = splittingLine + 
            "\t You can\'t add an empty event, goshujin-sama.\n" + splittingLine;
    /** String for error handling in case of data file not found on startup. */
    public static final String fileNotFoundError = splittingLine + 
            "\t File not fowund, goshujin-sama.\n" + splittingLine;
    /** String after creating data file. */
    public static final String newFileCreatedMessage = splittingLine + 
            "\t New file created, goshujin-sama.\n" + splittingLine;
    /** String for listing tasks. */
    public static final String listString = splittingLine + "\t Here are your tasks, goshujin-sama:\n";
}


