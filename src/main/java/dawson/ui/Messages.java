package dawson.ui;

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

    public static String[] getAddSuccessMessage(String taskName, int taskListSize) {
        return new String[] {
            "Got it. I've added this task:",
            "  " + taskName,
            String.format("Now you have %d tasks in the list.", taskListSize)
        };
    }
}
