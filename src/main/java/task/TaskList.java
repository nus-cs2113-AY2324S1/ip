package task;

import java.util.ArrayList;

/**
 * Represents a list of tasks
 */
public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>(100);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int taskId) {
        return this.tasks.get(taskId);
    }

    /**
     * Add a todo to the list
     *
     * @param description task description
     * @return the todo task created
     */
    public Todo addTodo(String description) {
        Todo todo = new Todo(description);
        this.tasks.add(todo);
        return todo;
    }

    /**
     * Add a deadline to the list
     *
     * @param description task description
     * @param by due date/time
     * @return the deadLine task created
     */
    public Deadline addDeadline(String description, String by) {
        Deadline deadline = new Deadline(description, by);
        this.tasks.add(deadline);
        return deadline;
    }

    /**
     * Add an event to the list
     *
     * @param description description of the event
     * @param startTime the starting time of the event
     * @param endTime the ending time of the event
     * @return the event task created
     */
    public Event addEvent(String description, String startTime, String endTime) {
            Event event = new Event(description, startTime, endTime);
            this.tasks.add(event);
            return event;
    }

    /**
     * Mark the task with the given task id to be done
     *
     * @param taskId task id to be marked
     */
    public void mark(int taskId) {
        Task task = this.tasks.get(taskId);
        task.setDone(true);
    }

    /**
     * Unmark a task with the given task id to be undone
     *
     * @param taskId task id to be unmarked
     */
    public void unmark(int taskId) {
        Task task = this.tasks.get(taskId);
        task.setDone(true);
    }

    /**
     * Delete a task with given task id
     *
     * @param taskId the id of the task to be deleted
     * @return the deleted task
     */
    public Task delete(int taskId) {
            Task task = this.tasks.get(taskId);
            this.tasks.remove(taskId - 1);
            return task;
    }

    /**
     * Find tasks with matching keyword
     *
     * @param keyword keyword to be found in taskList
     * @return Arraylist of matching tasks found
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Gather all tasks formatted to input format and put them in a list
     * to be saved in file by storage
     * @return string of formatted tasks
     */
    public String prepareSaveList() {
        StringBuilder listOfFormattedTasks = new StringBuilder();
        for (Task task : this.tasks) {
            listOfFormattedTasks.append(task.formatAsInput()).append("\n");
        }
        return listOfFormattedTasks.toString();
    }

    /**
     * Print out task in a list for list command
     *
     * @return string of task in a list
     */
    @Override
    public String toString() {
        if (this.getSize() == 0) {
            return "You have no task in your list!\n";
        }

        StringBuilder output = new StringBuilder();

        for (int i = 1; i <= tasks.size(); i++) {
            output.append(i).append(".").append(this.tasks.get(i - 1)).append("\n");
        }
        return output.toString();
    }
}
