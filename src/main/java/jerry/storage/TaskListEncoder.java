package jerry.storage;

import java.util.ArrayList;
import java.util.List;

import jerry.task.Task;
import jerry.task.TaskList;

/**
 * Encodes the {@code TaskList} object into a data file for storage.
 */
public class TaskListEncoder {

    /**
     * Encodes all the {@code Task} in the {@code toSave} into a list of decodable and readable string presentation
     * for storage.
     */
    public static List<String> encodeTaskList(TaskList toSave) {
        final List<String> encodedTasks = new ArrayList<>();
        toSave.getAllTasks().forEach(task -> encodedTasks.add(task.encode()));
        return encodedTasks;
    }
}
