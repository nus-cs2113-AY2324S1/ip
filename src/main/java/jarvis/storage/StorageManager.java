package jarvis.storage;

import jarvis.exception.JarvisException;
import jarvis.tasklist.TaskList;
import jarvis.tasks.Task;

import java.io.FileWriter;
import java.io.IOException;

public class StorageManager {

    /**
     * Writes a string of data to a file at the specified path.
     *
     * @param filePath  Path to the file where data should be written.
     * @param dataToAdd String of data to write to the file.
     * @param toAppend  If true, the data will be appended to the file; otherwise, the file will be overwritten.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    static void writeToFile(String filePath, String dataToAdd, boolean toAppend) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, toAppend);
        fileWriter.write(dataToAdd);
        fileWriter.close();
    }
    /**
     * Handles the creation and status setting of a Todo task.
     *
     * @param tasks             The TaskList instance to which the task should be added.
     * @param taskIndex         The index at which the task should be marked as done, if applicable.
     * @param taskDoneStatus    String indicating whether the task is done ("done") or not ("undone").
     * @param taskDescription   Description of the task.
     * @throws JarvisException  If an error occurs during task creation or status setting.
     */
    static void handleTodoTask(TaskList tasks, int taskIndex, String taskDoneStatus, String taskDescription) throws JarvisException {
        tasks.addToTaskList("todo " + taskDescription, Task.TaskType.TODO, false);
        if ("done".equals(taskDoneStatus)) {
            tasks.markTaskAsDone(taskIndex, false);
        }
    }
    /**
     * Handles the creation and status setting of a Deadline task.
     *
     * @param tasks             The TaskList instance to which the task should be added.
     * @param taskIndex         The index at which the task should be marked as done, if applicable.
     * @param taskDoneStatus    String indicating whether the task is done ("done") or not ("undone").
     * @param taskDescription   Description of the task.
     * @param dueTimeString     String representation of the task's due time.
     * @throws JarvisException  If an error occurs during task creation or status setting.
     */
    static void handleDeadlineTask(TaskList tasks, int taskIndex, String taskDoneStatus, String taskDescription, String dueTimeString) throws JarvisException {
        String dueTime = dueTimeString.replace("(by:", "").replace(")", "").strip();
        tasks.addToTaskList("deadline " + taskDescription + " /by " + dueTime, Task.TaskType.DEADLINE, false);
        if ("done".equals(taskDoneStatus)) {
            tasks.markTaskAsDone(taskIndex, false);
        }
    }
    /**
     * Handles the creation and status setting of an Event task.
     *
     * @param tasks             The TaskList instance to which the task should be added.
     * @param taskIndex         The index at which the task should be marked as done, if applicable.
     * @param taskDoneStatus    String indicating whether the task is done ("done") or not ("undone").
     * @param taskDescription   Description of the task.
     * @param timeString        String representation of the task's scheduled time.
     * @throws JarvisException  If an error occurs during task creation or status setting.
     */
    static void handleEventTask(TaskList tasks, int taskIndex, String taskDoneStatus, String taskDescription, String timeString) throws JarvisException {
        tasks.addToTaskList("event " + taskDescription + timeString, Task.TaskType.EVENT, false);
        if ("done".equals(taskDoneStatus)) {
            tasks.markTaskAsDone(taskIndex, false);
        }
    }
}
