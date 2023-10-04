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

    // public Task removeTask(int index) throws TaskNotFoundException {
    //     if (index < 1 || index > tasks.size()) {
    //         throw new TaskNotFoundException(this.getTaskNotFoundMessage(index));
    //     }
    //     Task task = this.getTaskByIndex(index);
    //     this.tasks.remove(index - 1);
    //     return task;
    // }
    
    public void removeTask(Task task) throws TaskNotFoundException {
        allTasks.remove(task);
    }

    public int size() {
        return allTasks.size();
    }

    public Boolean isEmpty() {
        return allTasks.isEmpty();
    }

    private String getTaskNotFoundMessage(int index) {
        if (index <= 0) {
            return TASK_INDEX_MUST_BE_POSITIF_MESSAGE;
        }
        switch (allTasks.size()) {
            case 0:
            return TASK_LIST_EMPTY_MESSAGE;
            case 1:
            return ONLY_ONE_TASK_MESSAGE;
            default:
            return String.format("The task number must be between 1 and %d.", allTasks.size());
        }
    }

    public Task getTaskByIndex(int index) {
        // if (index < 1 || index > tasks.size()) {
        //     throw new TaskNotFoundException(this.getTaskNotFoundMessage(index));
        // }
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

    public void serializeToFile(Path filePath) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(filePath.toString());
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(this);
        }
    }

    public static TaskList deserializeFromFile(Path filePath) throws IOException, ClassNotFoundException {
        try (FileInputStream fileIn = new FileInputStream(filePath.toString());
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            TaskList tasks = (TaskList) objectIn.readObject();
            return tasks;
        }
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
     * Returns a new UniquePersonList of all persons in the PersonBook at the time of the call.
     */
    public List<Task> getAllTasks() {
        return allTasks;
    }

}
