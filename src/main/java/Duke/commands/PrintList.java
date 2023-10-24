package duke.commands;

import duke.inputProcess.TaskList;

/**
 * The `PrintList` class is responsible for displaying the task list in the Hilary robot.
 * It retrieves the task list and prints each task to the user, along with the total number of tasks.
 * This class provides a convenient way for the user to view the contents of their task list.
 *
 * @author Cheung Ka Yuen
 * @version Final
 * @since 2023-10-24
 */
public class PrintList {
    private final TaskList tasks;

    /**
     * Constructs a `PrintList` object with the given task list.
     *
     * @param tasks The task list to be printed.
     */
    public PrintList(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints the list of tasks.
     * It also displays the total number of tasks in the list.
     */
    public void print() {
        for (int i = 0; i < tasks.getList().size(); ++i) {
            System.out.print("\t" + (i + 1) + ".");
            System.out.println(tasks.getList().get(i));
        }
        System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list");
    }
}
