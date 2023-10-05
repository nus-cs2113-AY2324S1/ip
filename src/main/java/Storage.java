import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private static String DATA_FILE_PATH = "ken.txt";

    public Storage(String filePath) {
        DATA_FILE_PATH = filePath;
    }

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


    public static void saveTasks(TaskList taskList) {
        File file = new File(DATA_FILE_PATH);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < taskList.getTaskDescriptions().size(); i++) {
                String taskType = taskList.getTaskTypes().get(i);
                String doneStatus = taskList.getTaskDoneStatus().get(i) ? "1" : "0";
                String description = taskList.getTaskDescriptions().get(i);
                String date = taskList.getTaskDates().get(i); // Add this line to get the date

                // Use the same separator character "|" for consistency
                writer.write(taskType + " | " + doneStatus + " | " + description + " | " + date);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to the data file.");
            e.getMessage();
            e.printStackTrace();
        }
    }


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