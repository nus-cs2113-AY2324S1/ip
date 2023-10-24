package duke.commands;

import duke.Task;
import duke.inputProcess.TaskList;

/**
 * The `FindTasks` class is responsible for searching and displaying tasks
 * in the task list from Duke robot that match a given keyword.
 * It searches the task list for tasks containing the specified keyword in descriptions.
 */
public class FindTasks {
    private final String userInput;
    private final TaskList tasks;

    /**
     * Constructs a `FindTasks` object with the given user input keyword and task list.
     *
     * @param userInput The keyword to search for in task descriptions.
     * @param tasks The task list in which to search for matching tasks.
     */
    public FindTasks(String userInput, TaskList tasks) {
        this.userInput = userInput;
        this.tasks = tasks;
    }

    /**
     * Searches the task list for tasks with descriptions that contain the specified keyword.
     * It displays the matching tasks.
     * If no matches are found, a message informing user is displayed.
     */
    public void find() {
        int count = 1;
        for (Task task : tasks.getList()) {
            if (task.getDescription().toLowerCase().contains(userInput.toLowerCase())) {
                if (count == 1) {
                    System.out.println("Here are the matching tasks in your list:");
                }
                System.out.println("\t" + count + ". " + task);
                count++;
            }
        }
        if (count == 1) {
            System.out.println("\tCannot find tasks with the keyword: " + userInput);
        }
    }
}
