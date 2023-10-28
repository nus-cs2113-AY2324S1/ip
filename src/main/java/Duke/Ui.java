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
        System.out.println("Hilary - Task Management Robot");
        System.out.println("--------------------------------");
        System.out.println("Available Commands:");
        System.out.println("- Add a Todo task: 'todo task'");
        System.out.println("- Add a Deadline task: 'deadline task /by dd/MM/yyyy HHmm'");
        System.out.println("- Add an Event task: 'event task /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm'");
        System.out.println("- Delete a task: 'delete taskIndex'");
        System.out.println("- Mark a task as done: 'mark taskIndex'");
        System.out.println("- Unmark a task as done: 'unmark taskIndex'");
        System.out.println("- Show the task list: 'list'");
        System.out.println("- Search for tasks by name: 'find taskName'");
        System.out.println("- Quit the application: 'bye'");
    }

}
