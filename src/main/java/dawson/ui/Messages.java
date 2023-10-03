package dawson.ui;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String[] MESSAGE_WELCOME = {
        "Hello! My name is: ",
        " _____     ______     __     __     ______     ______     __   __    ",
        "/\\  __-.  /\\  __ \\   /\\ \\  _ \\ \\   /\\  ___\\   /\\  __ \\   /\\ \"-.\\ \\   ",
        "\\ \\ \\/\\ \\ \\ \\  __ \\  \\ \\ \\/ \".\\ \\  \\ \\___  \\  \\ \\ \\/\\ \\  \\ \\ \\-.  \\  ",
        " \\ \\____-  \\ \\_\\ \\_\\  \\ \\__/\".~\\_\\  \\/\\_____\\  \\ \\_____\\  \\ \\_\\\\\"\\_\\ ",
        "  \\/____/   \\/_/\\/_/   \\/_/   \\/_/   \\/_____/   \\/_____/   \\/_/ \\/_/ ",
        "",
        "What can I do for you?"
    };

    public static final String MESSAGE_BYE = " Bye. Hope to see you again soon!";

    public static final String MESSAGE_INVALID_COMMAND = " Invalid command! Please enter a valid command";

    public static final String MESSAGE_PARSE_TASK_ERROR = "Error parsing tasks from storage!";

    /**
     * Generates a success message for adding a Todo, Deadline or Event task, 
     * including task details and the updated task list size.
     *
     * @param taskName     The name or description of the added task.
     * @param taskListSize The current size of the task list after adding the task.
     * @return Success message for adding Todo, Deadline or Event tasks.
     */
    public static String[] getAddSuccessMessage(String taskName, int taskListSize) {
        return new String[] {
            "Got it. I've added this task:",
            "  " + taskName,
            String.format("Now you have %d tasks in the list.", taskListSize)
        };
    }
}
