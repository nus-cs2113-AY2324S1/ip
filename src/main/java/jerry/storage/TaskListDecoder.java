package jerry.storage;

import java.util.List;

import jerry.task.Task;
import jerry.task.Todo;
import jerry.task.Deadline;
import jerry.task.Event;
import jerry.task.TaskList;
import jerry.exceptions.IllegalValueException;
import jerry.storage.StorageFile.StorageOperationException;

/**
 * Decodes the storage data file into an {@code TaskList} object.
 */
public class TaskListDecoder {

    /**
     * Decodes {@code encodedTaskList} into an {@code TaskList} containing the decoded tasks.
     *
     * @throws IllegalValueException if any of the fields in any encoded task string is invalid.
     * @throws StorageOperationException if the {@code encodedTaskList} is in an invalid format.
     */
    public static TaskList decodeTaskList(List<String> encodedTaskList)
    throws IllegalValueException {
        final TaskList decodedTaskList = new TaskList();
        Task newTask;
        for (String encodedTask : encodedTaskList) {
            decodedTaskList.addTask(decodeTaskFromString(encodedTask));
        }
        return decodedTaskList;
    }

    /**
     * Decodes {@code encodedTask} into a {@code Task}.
     *
     * @throws IllegalValueException if any field in the {@code encodedTask} is invalid.
     * @throws StorageOperationException if {@code encodedTask} is in an invalid format.
     */
    private static Task decodeTaskFromString(String encodedTask)
    throws IllegalValueException {
        Task task = null;
        switch (getEncodedTaskType(encodedTask)) {
            case 'T':
            task = Todo.decode(encodedTask);
            break;
            case 'D':
            task = Deadline.decode(encodedTask);
            break;
            case 'E':
            task = Event.decode(encodedTask);
            break;
        }
        return task;
    }

    private static char getEncodedTaskType(String encodedTask) {
        return encodedTask.charAt(0);
    }

}
