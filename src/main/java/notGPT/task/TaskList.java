package notGPT.task;

import java.util.ArrayList;
import notGPT.storage.Storage;

/**
 * The TaskList class represents a list of tasks.
 * Contains methods to manage tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private int taskCount;

    /**
     * Initializes a new TaskList with data from storage.
     *
     * @param storage The storage object used to load tasks from a file.
     */
    public TaskList(Storage storage) {
        this.taskList = storage.getBuffer();
        this.taskCount = taskList.size();
    }

    /**
     * Adds a new Todo task to the task list.
     *
     * @param taskName The description of the Todo task.
     */
    public void addTodo(String taskName) {
        Task newTask = new ToDo(taskName);
        taskList.add(newTask);
        taskCount++;
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param taskName The description of the Deadline task.
     * @param deadline The deadline associated with the task.
     */
    public void addDeadline(String taskName, String deadline) {
        Task newTask = new Deadlines(taskName, deadline);
        taskList.add(newTask);
        taskCount++;
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param taskName  The description of the Event task.
     * @param startTime The start time of the event.
     * @param endTime   The end time of the event.
     */
    public void addEvent(String taskName, String startTime, String endTime) {
        Task newTask = new Event(taskName, startTime, endTime);
        taskList.add(newTask);
        taskCount++;
    }

    /**
     * Retrieves an array of task descriptions as strings.
     *
     * @return An array of task descriptions.
     */
    public String[] getTasks() {
        String[] tasks = new String[taskCount];
        for (int i = 0; i < taskCount; i++) {
            tasks[i] = taskList.get(i).toString();
        }
        return tasks;
    }

    /**
     * Marks a task as done.
     *
     * @param taskNumber The number of the task to mark as done.
     */
    public void markTaskAsDone(int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= taskCount) {
            taskList.get(taskNumber - 1).markAsDone();
        } else {
            System.out.println("Invalid task number.");
        }
    }

    /**
     * Unmarks a task as done.
     *
     * @param taskNumber The number of the task to unmark as done.
     */
    public void unmarkTaskAsDone(int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= taskCount) {
            taskList.get(taskNumber - 1).unmarkAsDone();
        } else {
            System.out.println("Invalid task number.");
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNumber The number of the task to delete.
     */
    public void deleteTask(int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= taskCount) {
            taskList.remove(taskNumber - 1);
            taskCount--;
        } else {
            System.out.println("Invalid task number.");
        }
    }

    /**
     * Finds tasks containing a keyword and returns them as an array of strings.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An array of matching task descriptions.
     */
    public String[] findTasks(String keyword) {
        ArrayList<String> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getTaskName().contains(keyword)) {
                matchingTasks.add(task.toString());
            }
        }
        return matchingTasks.toArray(new String[0]);
    }

    /**
     * Returns the count of tasks in the task list.
     *
     * @return The count of tasks.
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Retrieves a task by its number in the task list.
     *
     * @param taskNumber The number of the task to retrieve.
     * @return The task corresponding to the given number.
     */
    public Task getTaskByNumber(int taskNumber) {
        if (taskNumber >= 1 && taskNumber <= taskCount) {
            return taskList.get(taskNumber - 1);
        } else {
            System.out.println("Invalid task number.");
            return null;
        }
    }
}


