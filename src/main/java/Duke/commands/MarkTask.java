package duke.commands;

import duke.inputProcess.TaskList;

/**
 * The `MarkTask` class is responsible for marking tasks as done in the Hilary robot's task list.
 * It processes user input, identifies the task to be marked as done, and updates its status.
 * This class also handles error cases such as invalid input and out-of-bounds task indices.
 *
 * @author Cheung Ka Yuen
 * @version Final
 * @since 2023-10-24
 */
public class MarkTask {
    private final String userInput;
    private final TaskList tasks;
    private static final String MISSING_INDEX_MESSAGE = "OOPS!!! Need to enter the index of the task you want to mark as done";
    private static final String INDEX_OUT_OF_RANGE_MESSAGE = "OOPS!!! Need to input an index from the list";

    /**
     * Constructs a `MarkTask` object with the given user input and task list.
     *
     * @param userInput The user-provided index of the task to be marked as done in String.
     * @param tasks The task list where the task will be marked as done.
     */
    public MarkTask(String userInput, TaskList tasks) {
        this.userInput = userInput;
        this.tasks = tasks;
    }

    /**
     * Marks a task as done in the task list based on the user-provided index.
     * If the input is not a valid integer or the index is out of range, appropriate error messages are displayed.
     */
    public void mark() {
        try {
            int taskNumToMark = Integer.parseInt(userInput) - 1;
            tasks.getList().get(taskNumToMark).markAsDone();
            System.out.print("\tNice! I've marked this task as done:\n\t\t");
            System.out.println(tasks.getList().get(taskNumToMark));
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("\t" + MISSING_INDEX_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t" + INDEX_OUT_OF_RANGE_MESSAGE);
        }
    }
}
