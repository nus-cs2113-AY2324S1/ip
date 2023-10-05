import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Storage class handles loading and saving tasks to a data file.
 */
public class Storage {
    private static String DATA_FILE_PATH = "ken.txt";

    /**
     * Constructs a Storage object with a specified file path.
     *
     * @param filePath The path to the data file.
     */
    public Storage(String filePath) {
        DATA_FILE_PATH = filePath;
    }

    /**
     * Loads tasks from the data file and adds them to the given TaskList.
     *
     * @param taskList The TaskList to which tasks will be loaded.
     */
    public void loadTasks(TaskList taskList) {
        File file = new File(DATA_FILE_PATH);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    taskList.parseAndAddTask(line);
                }
            } catch (IOException e) {
                System.out.println("Error loading tasks from the data file.");
            }
        }
    }

    /**
     * Saves tasks from the given TaskList to the data file.
     *
     * @param taskList The TaskList containing tasks to be saved.
     */
    public static void saveTasks(TaskList taskList) {
        File file = new File(DATA_FILE_PATH);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < taskList.getTaskDescriptions().size(); i++) {
                String taskType = taskList.getTaskTypes().get(i);
                String doneStatus = taskList.getTaskDoneStatus().get(i) ? "1" : "0";
                String description = taskList.getTaskDescriptions().get(i);
                String date = taskList.getTaskDates().get(i);

                // Use the same separator character "|" for consistency
                writer.write(taskType + " | " + doneStatus + " | " + description + " | " + date);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to the data file.");
        }
    }

    /**
     * Deletes a task at the specified index in the TaskList and saves the updated list to the data file.
     *
     * @param taskIndex The index of the task to be deleted.
     * @param taskList  The TaskList from which the task will be deleted.
     */
    public static void deleteTask(int taskIndex, TaskList taskList) {
        if (taskIndex >= 0 && taskIndex < TaskList.taskDescriptions.size()) {
            TaskList.taskDescriptions.remove(taskIndex);
            TaskList.taskDoneStatus.remove(taskIndex);
            TaskList.taskTypes.remove(taskIndex);
            TaskList.taskDates.remove(taskIndex);

            // Call saveTasks to save the tasks after deleting
            saveTasks(taskList);
        } else {
            System.out.println("Task not found. Nothing was deleted.");
        }
    }
}
