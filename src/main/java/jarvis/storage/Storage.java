package jarvis.storage;

import jarvis.exception.JarvisException;
import jarvis.tasks.Task;
import jarvis.tasklist.TaskList;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Storage {
    private final File dataFile;
    /**
     * Initializes a new Storage instance.
     *
     * @param filePath The file path of the data storage file.
     */
    public Storage(String filePath){
        dataFile = new File(filePath);
    }

    /**
     * Clears all content in the data file.
     *
     * @throws IOException If an I/O error occurs during file writing.
     */
    private void clearFileContent() throws IOException {
        try (FileWriter fileWriter = new FileWriter(dataFile, false)) {
            fileWriter.write("");
        } catch (IOException e) {
            throw new IOException("An error occurred while trying to clear the file: " + e.getMessage(), e);
        }
    }
    /**
     * Loads tasks from the data file into the application.
     *
     * @param tasks TaskList instance to populate with loaded tasks.
     * @throws JarvisException If invalid task data is encountered.
     * @throws IOException If an I/O error occurs.
     */
    public void loadData(TaskList tasks) throws JarvisException, IOException {
        buildTaskListFromFile(tasks);
        if (tasks.getTaskListSize() > 0) {
            System.out.println("You currently have the following tasks sir");
            tasks.printTaskList();
        } else {
            System.out.println("You currently have no saved tasks sir");
        }
    }

    /**
     * Populates the provided TaskList instance with tasks from the data file.
     *
     * @param tasks TaskList instance to populate.
     * @throws IOException If an I/O error occurs during file reading.
     * @throws JarvisException If invalid task data is encountered.
     */
    private void buildTaskListFromFile(TaskList tasks) throws IOException, JarvisException {
        try {
            if (dataFile.createNewFile()) {
                System.out.println("Task-list created: " + dataFile.getName());
            }
        } catch (IOException invalidFilePath) {
            System.out.println(invalidFilePath.getMessage());
        }

        try (Scanner fileScanner = new Scanner(dataFile)) {
            int taskIndex = 0;
            while (fileScanner.hasNext()) {
                taskIndex++;
                String nextTask = fileScanner.nextLine();
                String[] taskSubStrings = nextTask.split("\\|");

                String taskType = taskSubStrings[0].strip();
                String taskDoneStatus = taskSubStrings[1].strip();
                String taskDescription = taskSubStrings[2].strip();

                switch (taskType) {
                case "T":
                    StorageManager.handleTodoTask(tasks, taskIndex, taskDoneStatus, taskDescription);
                    break;
                case "D":
                    String dueTimeString = taskSubStrings[3].strip();
                    StorageManager.handleDeadlineTask(tasks, taskIndex, taskDoneStatus, taskDescription, dueTimeString);
                    break;
                case "E":
                    String eventTimeString = " /from " + taskSubStrings[3].strip() + " /to " + taskSubStrings[4].strip();
                    StorageManager.handleEventTask(tasks, taskIndex, taskDoneStatus, taskDescription, eventTimeString);
                    break;
                default:
                    System.out.println(nextTask);
                    break;
                }
            }
        } catch (IOException invalidFilePath) {
            System.out.println("    " + invalidFilePath.getMessage());
        }
    }
    /**
     * Updates the data file to reflect the current state of the provided TaskList instance.
     * Clears the data file and repopulates it with the serialized task data present within
     * the provided TaskList instance.
     * @param tasks The current task list.
     */
    public void updateFile(TaskList tasks){
        try{
            clearFileContent();
            ArrayList<Task> taskList = tasks.getTaskList();
            for (Task task: taskList) {
                switch (task.getTaskType()) {
                case TODO:
                    if (task.taskIsDone()) {
                        StorageManager.writeToFile(dataFile.getPath(), "T | done |  " + task.getDescription()
                                + System.lineSeparator(), true);
                    } else {
                        StorageManager.writeToFile(dataFile.getPath(), "T | undone |  " + task.getDescription()
                                + System.lineSeparator(), true);
                    }
                    break;
                case DEADLINE:
                    if (task.taskIsDone()) {
                        StorageManager.writeToFile(dataFile.getPath(), "D | done |  " + task.getDescription()
                                + " | " + task.getTime() + System.lineSeparator(), true);
                    } else {
                        StorageManager.writeToFile(dataFile.getPath(), "D | undone |  " + task.getDescription()
                                + " | " + task.getTime() + System.lineSeparator(), true);
                    }
                    break;
                case EVENT:
                    if (task.taskIsDone()) {
                        StorageManager.writeToFile(dataFile.getPath(), "E | done |  " + task.getDescription()
                                + " | " + task.getTime() + System.lineSeparator(), true);
                    } else {
                        StorageManager.writeToFile(dataFile.getPath(), "E | undone |  " + task.getDescription()
                                + " | " + task.getTime() + System.lineSeparator(), true);
                    }
                    break;
                }
            }
        }
        catch(NullPointerException | IOException invalidFilePath){
            System.out.println(invalidFilePath.getMessage());
        }
    }
}