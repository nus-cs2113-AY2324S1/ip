package duke;

/**
 * The `Ui` class provides methods for user interface and displaying messages to the user.
 */
public class Ui {
    /**
     * Displays a greeting message when the Hilary robot is launched.
     */
    public static void greeting() {
        System.out.println("Hello! I'm Bot Hilary");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays a help message providing instructions on how to use the Hilary robot.
     */
    public static void help() {
        System.out.println("This is a task management robot. You can add todo tasks, deadline tasks, and event tasks.");
        System.out.println("To add a todo task, simply input the format: \"todo task\"");
        System.out.println("To add a deadline task, simply input the format: \"deadline task deadlineTime\"");
        System
                .out.println("To add an event task, use the input format: \"event task eventTime\"");
        System.out.println("The time format for deadline tasks should be \"/by dd/MM/yyyy hhmm\", and for event tasks, it is \"/from dd/MM/yyyy hhmm /to dd/MM/yyyy hhmm\".");
        System.out.println("Delete a task: enter the command \"delete indexOfTheTask\"");
        System.out.println("Mark a task as done: enter the command \"mark indexOfTheTask\"");
        System.out.println("Unmark a task: enter the command \"unmark indexOfTheTask\"");
        System.out.println("Show what's in the list: enter the command \"list\"");
        System.out.println("Search for tasks by name: enter the command \"find taskName\"");
        System.out.println("Quit: Enter the command \"bye\"");
    }
}
