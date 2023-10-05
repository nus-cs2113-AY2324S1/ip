package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper around the List<Task> to allow for controlled access and formatting methods.
 */
public class TaskList {
    private List<Task> tasks =  new ArrayList<>();
    public TaskList() {}

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Marks specific task as either done or undone
     * and returns the status of the marking.
     *
     * @param index of task to be marked.
     * @param isDone boolean representing status of the task.
     * @return Status of marking as string.
     */
    public String markTask(int index, boolean isDone) {
        final StringBuilder formatted = new StringBuilder();
        tasks.get(index).setStatus(isDone);

        if (isDone) {
            formatted.append("Nice! I've marked this task as done:").append("\n");
        } else {
            formatted.append("OK, I've marked this task as not done yet:").append("\n");
        }
        formatted.append(tasks.get(index).getFormattedTask());

        return formatted.toString();
    }


    /**
     * Adds a new task to the TaskList and returns
     * the status of the addition.
     *
     * @param task to be added
     * @return Status of the addition.
     */
    public String addTask(Task task) {
        tasks.add(task);

        return "Got it. I've added this task:\n" + task.getFormattedTask() + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Removes a task from the TaskList by index and
     * returns the status of the removal.
     *
     * @param idx of task to be removed
     * @return Status of the removal
     */
    public String removeTask(int idx) {
        Task removedTask = tasks.remove(idx);

        return "Got it. I've removed this task:\n" + removedTask.getFormattedTask() + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Returns a task object by index.
     *
     * @param idx Index of task to return.
     * @return task object
     */
    public Task get(int idx) {
        return tasks.get(idx);
    }

    /**
     * Returns a formatted version of all tasks
     * in TaskList with 1-based indexing.
     *
     * @return String formatted list
     */
    public String getIndexedTasks() {
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < tasks.size() && tasks.get(i) != null; i++) {
            formatted.append(i + 1).append(". ").append(tasks.get(i).getFormattedTask()).append("\n");
        }

        return formatted.toString();
    }

    /**
     * Returns a serialized version of all tasks
     * in TaskList for saving into the .txt file.
     *
     * @return String serialized list
     */
    public String getSerializedTasks() {
        StringBuilder formatted = new StringBuilder();
        for (Task task : tasks) {
            formatted.append(task.getSerializedString()).append("\n");
        }

        return formatted.toString();
    }

    /**
     * Returns a formatted version of all tasks
     * in TaskList with 1-based indexing that match
     * the specified keyword parameter.
     *
     * @param keyword string to match against description of tasks
     * @return String formatted list
     */
    public String getIndexedTasksByKeyword(String keyword) {
        int idx = 1;
        StringBuilder formatted = new StringBuilder();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                formatted.append(idx++).append(". ").append(task.getFormattedTask()).append("\n");
            }
        }

        return formatted.toString();
    }
}
