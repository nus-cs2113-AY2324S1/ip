package BotBuddy;

import java.util.ArrayList;
/**
 * Represents a task list.
 */
public class TaskList {
    private ArrayList<Task> taskArrayList;

    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    public ArrayList<Task> getTaskArrayList() {
        return taskArrayList;
    }

    /**
     * Adds a todo to the task list.
     *
     * @param description Description of the todo.
     */
    public void addTodoToTaskList(String description) {
        taskArrayList.add(new Todo(description));
    }

    /**
     * Adds an event to the task list.
     *
     * @param eventDetails Array with event description, event from, and event to in indexes 0, 1, 2 respectively.
     */
    public void addEventToTaskList(String[] eventDetails) {
        String eventName = eventDetails[0];
        String eventFrom = eventDetails[1];
        String eventTo = eventDetails[2];
        taskArrayList.add(new Event(eventName, eventFrom, eventTo));
    }

    /**
     * Adds a deadline to the task list.
     *
     * @param deadlineDetails Array with deadline description and due date in indexes 0 and 1 respectively.
     */
    public void addDeadlineToTaskList(String[] deadlineDetails) {
        String deadlineName = deadlineDetails[0];
        String deadlineBy = deadlineDetails[1];
        taskArrayList.add(new Deadline(deadlineName, deadlineBy));
    }

    /**
     * Prints tasks in the task list.
     */
    public void listTasksInTaskList(int noOfTasks) {
        for (int i = 0; i < noOfTasks; i++) {
            System.out.println(i + 1 + ". " + taskArrayList.get(i));
        }
    }

    /**
     * Marks a task as done in the task list.
     *
     * @param taskIndex Task index to mark as done.
     */
    public void markTaskInTaskList(int taskIndex) {
        taskArrayList.get(taskIndex).markAsDone();
    }

    /**
     * Unmarks a task as done in the task list.
     *
     * @param taskIndex Task index to unmark as done.
     */
    public void unmarkTaskInTaskList(int taskIndex) {
        taskArrayList.get(taskIndex).markAsUndone();
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskIndex Task index to delete.
     */
    public void removeTaskFromTaskList(int taskIndex) {
        taskArrayList.remove(taskIndex);
    }

    /**
     * Searches for tasks in the task list.
     *
     * @param searchString String to search for amongst tasks.
     * @param noOfTasks Number of tasks in the task list.
     */
    public void findTasksInTaskList(String searchString, int noOfTasks) {
        for (int i = 0; i < noOfTasks; i++) {
            String currentTask = String.valueOf(taskArrayList.get(i));
            if (currentTask.contains(searchString)) {
                System.out.println(i + 1 + ". " + taskArrayList.get(i));
            }
        }
    }
}