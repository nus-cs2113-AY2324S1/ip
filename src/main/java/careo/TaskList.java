package careo;

import java.util.ArrayList;

/**
 * Responsible for performing actions on the stored tasks.
 */
public class TaskList {
    /** UI that is responsible for user input and output */
    private Ui ui;
    /** Internal-only list of all tasks that make up this TaskList */
    private ArrayList<Task> tasks;

    /**
     * Instantiates a TaskList from a list of tasks and connects it to a Ui.
     *
     * @param tasks List of tasks that will be assigned to the internal tasks.
     * @param ui The Ui instance used by the main Duke instance.
     */
    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }


    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Computes and returns the number of tasks in this TaskList.
     *
     * @return The number of tasks in this TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Marks or unmarks a task from tasks and prints a confirmation.
     * Handles invalid indexes and prints a warning in that case.
     *
     * @param markOrUnmark Whether the task should be marked or unmarked.
     * @param taskIdx Index (zero-based) of the task that should be marked
     *                or unmarked in tasks.
     */
    public void markOrUnmarkTask(MarkOrUnmark markOrUnmark, int taskIdx) {
        if (tasks.isEmpty()) {
            ui.printTaskListEmpty();
            return;
        }

        if (taskIdx >= tasks.size() || taskIdx < 0) {
            ui.showInvalidTaskIndexError(this);
            return;
        }

        Task selectedTask = tasks.get(taskIdx);
        selectedTask.setIsDone(markOrUnmark == MarkOrUnmark.MARK);

        ui.printSuccessfulMarkOrUnmark(markOrUnmark, selectedTask);
    }

    /**
     * Adds a new task to tasks and prints a confirmation.
     *
     * @param newTask The task that shall be added.
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);

        ui.printSuccessfullyAddedTask(newTask, this);
    }

    /**
     * Removes a tasks from tasks and prints a confirmation.
     *
     * @param taskIdx Index of the task that shall be removed.
     */
    public void removeTask(int taskIdx) {
        Task removedTask = tasks.remove(taskIdx);

        ui.printSuccessfullyRemovedTask(removedTask, this);
    }

    public ArrayList<Task> findMatchingTasks(String searchTerm) {
        ArrayList<Task> matchingTasks = new ArrayList<Task>();

        for (Task task : tasks) {
            if (task.getDescription().contains(searchTerm)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }
}
