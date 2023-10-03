package jarvis.storage;

import jarvis.exception.JarvisException;
import jarvis.tasklist.TaskList;
import jarvis.tasks.Task;

import java.io.FileWriter;
import java.io.IOException;

public class StorageManager {

    /**
     * Write new data to file
     * @param filePath - specify file location where data is written to
     * @param dataToAdd - data to be written
     * @param toAppend - if True, data is added to the back of the existing data
     **/
    static void writeToFile(String filePath, String dataToAdd, boolean toAppend) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, toAppend);
        fileWriter.write(dataToAdd);
        fileWriter.close();
    }

    static void handleTodoTask(TaskList tasks, int taskIndex, String taskDoneStatus, String taskDescription) throws JarvisException {
        tasks.addToTaskList("todo " + taskDescription, Task.TaskType.TODO, false);
        if ("done".equals(taskDoneStatus)) {
            tasks.markTaskAsDone(taskIndex, false);
        }
    }

    static void handleDeadlineTask(TaskList tasks, int taskIndex, String taskDoneStatus, String taskDescription, String dueTimeString) throws JarvisException {
        String dueTime = dueTimeString.replace("(by:", "").replace(")", "").strip();
        tasks.addToTaskList("deadline " + taskDescription + " /by " + dueTime, Task.TaskType.DEADLINE, false);
        if ("done".equals(taskDoneStatus)) {
            tasks.markTaskAsDone(taskIndex, false);
        }
    }

    static void handleEventTask(TaskList tasks, int taskIndex, String taskDoneStatus, String taskDescription, String timeString) throws JarvisException {
        tasks.addToTaskList("event " + taskDescription + timeString, Task.TaskType.EVENT, false);
        if ("done".equals(taskDoneStatus)) {
            tasks.markTaskAsDone(taskIndex, false);
        }
    }
}
