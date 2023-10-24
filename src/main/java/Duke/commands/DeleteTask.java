package duke.commands;

import duke.inputProcess.TaskList;

/**
 * The `DeleteTask` class is responsible for deleting tasks from the task list in the Hilary robot.
 * It processes user input, identifies the task to be deleted, and performs the deletion.
 * This class also handles error cases such as invalid input and out-of-bounds task indices.
 *
 * @author Cheung Ka Yuen
 * @version Final
 * @since 2023-10-24
 */
public class DeleteTask {
    private String userInput;
    private TaskList tasks;

    /**
     * Constructs a `DeleteTask` object with the given user input and task list.
     *
     * @param userInput The user-provided index of the task to be deleted in String.
     * @param tasks The task list from which the task will be deleted.
     */
    public DeleteTask(String userInput, TaskList tasks) {
        this.userInput = userInput;
        this.tasks = tasks;
    }

    /**
     * Deletes a task from the task list based on the user input index.
     * If the input is not a valid integer or the index is out of range, appropriate error messages are displayed.
     */
    public void delete() {
        int taskNumToDelete;
        try {
            taskNumToDelete = Integer.parseInt(userInput) - 1;
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("\tOOPS!!! Need to enter the index of the task you want to delete");
            return;
        }
        try {
            System.out.println("\tNoted. I've removed this task:\n\t\t" +
                    tasks.getList().get(taskNumToDelete));
            tasks.getList().remove(taskNumToDelete);
            System.out.println("\tNow you have " + tasks.getList().size() + " in the list");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tOOPS!!! Need to input an index from the list");
        }
    }
}
