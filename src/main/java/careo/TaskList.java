package careo;

import java.util.ArrayList;

public class TaskList {
    private Ui ui;
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }


    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Marks or unmarks a task from tasks and prints a confirmation. Handles invalid indexes and
     * prints a warning in that case.
     *
     * @param markOrUnmark Whether the task should be marked or unmarked.
     * @param taskIdx      Index (zero-based) of the task that should be marked/unmarked in tasks.
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
