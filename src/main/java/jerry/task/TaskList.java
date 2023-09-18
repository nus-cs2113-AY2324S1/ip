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

import jerry.task.Task;
import jerry.Jerry;
import jerry.exceptions.TaskNotFoundException;

public class TaskList implements Serializable {
    private List<Task> tasks;

    private static final String TASK_LIST_EMPTY_MESSAGE = "You haven't added any taskList yet.";
    private static final String ONLY_ONE_TASK_MESSAGE = "You have added only one task yet.";
    private static final String TASK_INDEX_MUST_BE_POSITIF_MESSAGE = "Task index must be a positive number.";

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int size() {
        return this.tasks.size();
    }

    public Boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    private String getTaskNotFoundMessage(int index) {
        if (index <= 0) {
            return TASK_INDEX_MUST_BE_POSITIF_MESSAGE;
        }
        switch (this.tasks.size()) {
            case 0:
            return TASK_LIST_EMPTY_MESSAGE;
            case 1:
            return ONLY_ONE_TASK_MESSAGE;
            default:
            return String.format("The task number must be between 1 and %d.", this.tasks.size());
        }
    }

    public Task getTaskByIndex(int index) throws TaskNotFoundException {
        if (index < 1 || index >= tasks.size()) {
            throw new TaskNotFoundException(this.getTaskNotFoundMessage(index));
        }
        return this.tasks.get(index - 1);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append(String.format("\t%d. %s\n",i + 1, tasks.get(i)));
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
}
