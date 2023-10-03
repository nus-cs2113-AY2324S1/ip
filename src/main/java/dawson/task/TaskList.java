package dawson.task;

import dawson.exception.DawsonException;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a task list that stores Todo, Deadline and Event tasks
 * in an ArrayList. <p>Contains utility methods to access and modify the task list
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the task list.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Marks a task at the specified index in the task list as done.
     *
     * @param index The index of the task in the task list to be marked as done (0-based indexing).
     * @return The task that has been marked as done.
     * @throws DawsonException If the index is out of range of the task list.
     */
    public Task markAsDoneIndex(int index) throws DawsonException {
        try {
            Task task = taskList.get(index);
            task.markAsDone();
            return task;

        } catch (IndexOutOfBoundsException e) {
            // Convert to 1-base indexing to show error
            String errorMsg = (index+1) + " index out of range of task list!";
            throw new DawsonException(errorMsg);
        }
    }

    /**
     * Unmarks a task at the specified index in the task list.
     *
     * @param index The index of the task in the task list to be unmarked (0-based indexing).
     * @return The task that has been unmarked.
     * @throws DawsonException If the index is out of range of the task list.
     */
    public Task unmarkIndex(int index) throws DawsonException {
        try {
            Task task = taskList.get(index);
            task.unmark();
            return task;

        } catch (IndexOutOfBoundsException e) {
            // Convert to 1-base indexing to show error
            String errorMsg = (index+1) + " index out of range of task list!";
            throw new DawsonException(errorMsg);
        }
    }

    /**
     * Deletes a task at the specified index from the task list.
     *
     * @param index The index of the task in the task list to be deleted (0-based indexing).
     * @return The task that has been deleted from the task list.
     * @throws DawsonException If the index is out of range of the task list.
     */
    public Task deleteTask(int index) throws DawsonException {
        try {
            Task removedTask = taskList.remove(index);
            return removedTask;

        } catch (IndexOutOfBoundsException e) {
            // Convert to 1-base indexing to show error
            String errorMsg = (index+1) + " index out of range of task list!";
            throw new DawsonException(errorMsg);
        }
    }

    /**
     * Finds tasks that contain the specified query string in their descriptions and returns them as a list of strings.
     *
     * @param query The query string to search for within task descriptions.
     * @return A list of strings containing tasks with descriptions matching the query string.
     */
    public ArrayList<String> findTasks(String query) {
        ArrayList<String> result = new ArrayList<>();

        int counter = 1;
        for (Task task : taskList) {
            if (task.toString().contains(query)) {
                String line = String.format("%d. %s", counter, task);
                result.add(line);
                counter++;
            }
        }

        return result;
    }

    /**
     * Retrieves the entire task list as a list of strings.
     *
     * @return A list of strings representing all tasks.
     */
    public ArrayList<String> getTaskList() {
        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < taskList.size(); i++) {
            String line = String.format("%d. %s", i + 1, taskList.get(i));
            result.add(line);
        }

        return result;
    }

    /**
     * Finds tasks with due dates or event dates that match the specified query date and returns them as a list of strings.
     *
     * @param queryDate The date to search for within deadline and event tasks.
     * @return A list of strings containing tasks that matches the queryDate
     */
    public ArrayList<String> findTasksWithDate(LocalDate queryDate) {
        ArrayList<String> result = new ArrayList<String>();
        if (queryDate == null) {
            return result;
        }

        int counter = 1;
        for (Task task : taskList) {
            if (task instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                if (deadlineTask.containsQueryDate(queryDate)) {
                    String line = String.format("%d. %s", counter, task);
                    result.add(line);
                    counter++;
                }

            } else if (task instanceof EventTask) {
                EventTask eventTask = (EventTask) task;
                if (eventTask.containsQueryDate(queryDate)) {
                    String line = String.format("%d. %s", counter, task);
                    result.add(line);
                    counter++;
                }
            }
        }

        return result;
    }

    /**
     * Encodes the entire task list into a single string representation. 
     * Utilises abstract encode method of Task class to encode each task
     *
     * @return A string containing the encoded representation of all tasks in the task list.
     */
    public String encodeTaskList() {
        boolean firstLine = true;
        StringBuilder result = new StringBuilder();

        for (Task task : taskList) {
            String taskString = task.encode();
            if (!firstLine) { // Only add next line after first line
                result.append(System.lineSeparator());
            }
            result.append(taskString);
            firstLine = false;
        }

        return result.toString();
    }
}
