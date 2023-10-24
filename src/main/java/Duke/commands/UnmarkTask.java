package duke.commands;

import duke.inputProcess.TaskList;

/**
 * The `UnmarkTask` class is responsible for marking tasks as not done in the Duke robot.
 * It allows users to specify the task to be unmark by its index.
 */
public class UnmarkTask {
    private final String userInput;
    private final TaskList tasks;

    /**
     * Constructs an `UnmarkTask` object with the given user input and task list.
     *
     * @param userInput The user-provided index of the task to be unmarked as not done.
     * @param tasks The task list where the task will be unmarked.
     */
    public UnmarkTask(String userInput, TaskList tasks) {
        this.userInput = userInput;
        this.tasks = tasks;
    }

    /**
     * Unmarks a task as not done in the task list based on the user-provided index.
     * If the input is not a valid integer or the index is out of range, appropriate error messages are displayed.
     */
    public void unmark() {
        int taskNumToUnmark;
        try {
            taskNumToUnmark = Integer.parseInt(userInput) - 1;
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("\tOOPS!!! Need to enter the index of the task you want to unmark as not done");
            return;
        }
        try {
            tasks.getList().get(taskNumToUnmark).unmark();
            System.out.print("\tOK, I've marked this task as not done yet:\n\t\t");
            System.out.println(tasks.getList().get(taskNumToUnmark));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tOOPS!!! Need to input an index from the list");
        }
    }
}
