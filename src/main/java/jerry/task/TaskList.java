package jerry.task;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Path;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collections;

import jerry.task.Task;
import jerry.Main;
import jerry.exceptions.TaskNotFoundException;

/**
 * Represents the entire taks list. Contains the tasks data.
 */
public class TaskList implements Serializable {
    private List<Task> allTasks;

    private List<Task> lastShownList = Collections.emptyList();

    private static final String TASK_LIST_EMPTY_MESSAGE = "You haven't added any taskList yet.";
    private static final String ONLY_ONE_TASK_MESSAGE = "You have added only one task yet.";
    private static final String TASK_INDEX_MUST_BE_POSITIF_MESSAGE = "Task index must be a positive number.";

    /**
     * Creates an empty address book.
     */
    public TaskList() {
        allTasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        allTasks.add(task);
    }

    
    public void removeTask(Task task) throws TaskNotFoundException {
        allTasks.remove(task);
    }

    /**
     * Get the right task given an index
     *
     * @return the task
     */
    public Task getTaskByIndex(int index) {
        return allTasks.get(index - 1);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < allTasks.size(); i++) {
            stringBuilder.append(String.format("\t%d. %s\n",i + 1, allTasks.get(i)));
        }

        return stringBuilder.toString();
    }

    /**
     * Updates the {@link #lastShownList} if the result contains a list of Tasks.
     */
    public void setLastShownList(List<Task> listInResult) {
        if (listInResult != null) {
            lastShownList = listInResult;
        }
    }

    /**
     * Returns a new list of all tasks in the taskList at the time of the call.
     */
    public List<Task> getAllTasks() {
        return allTasks;
    }

}
