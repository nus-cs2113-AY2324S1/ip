package AMY;

import AMY.command.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class Storage {
    public static String FILE_PATH = "./data/AMY.txt";
    public static int totalTasks;
    public Storage(String filePath) {
        FILE_PATH = filePath;
    }

    // Load tasks from a file to show saved tasks when the program starts
    public static void loadTasksFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create parent directories if they don't exist
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            int numberOfTasks = 0; // Initialize numberOfTasks
            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                Task task = Task.parseFromFileString(taskData);
                if (task != null) {
                    TaskList.addToList(task);
                    numberOfTasks++; // Increment numberOfTasks
                }
            }
            scanner.close();
            totalTasks = numberOfTasks; // Update numberOfTasks in AMY class
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Creating a new file.");
        } catch (IOException e) {
            System.out.println("☹ OOPS!!! Error loading tasks from the file.");
        }
    }

    // Save tasks to a file to load later when command is entered
    public static void saveTasksToFile() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : TaskList.taskList) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("☹ OOPS!!! Error saving tasks to the file.");
        }
    }
}