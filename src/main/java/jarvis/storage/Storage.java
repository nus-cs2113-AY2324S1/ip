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
    public Storage(String filePath){
        dataFile = new File(filePath);
    }

    /**
     * Clear the content of a file.
     * @throws IOException If an I/O error occurs.
     */
    private void clearFileContent() throws IOException {
        try (FileWriter fileWriter = new FileWriter(dataFile, false)) {
            fileWriter.write("");
        } catch (IOException e) {
            throw new IOException("An error occurred while trying to clear the file: " + e.getMessage(), e);
        }
    }

    public void loadData(TaskList tasks) throws JarvisException, IOException {
        buildTaskListFromFile(tasks);
        if (tasks.getTaskListSize() > 0) {
            System.out.println("    You currently have the following tasks");
            tasks.printTaskList();
        } else {
            System.out.println("    You currently have no saved tasks");
        }
    }

    /**
     * Build the array list from the local datafile
     * @param tasks - load tasklist array from TaskList
     */
    private void buildTaskListFromFile(TaskList tasks) throws IOException, JarvisException {
        try {
            if (dataFile.createNewFile()) {
                System.out.println("    Task-list created: " + dataFile.getName());
            }
        } catch (IOException invalidFilePath) {
            System.out.println("    " + invalidFilePath.getMessage());
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
                    StorageManager.handleDeadlineTask(tasks, taskIndex, taskDoneStatus, taskDescription, taskSubStrings[3]);
                    break;
                case "E":
                    StorageManager.handleEventTask(tasks, taskIndex, taskDoneStatus, taskDescription, taskSubStrings[3]);
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
     * update the text file at each instance of edit
     * clear file content before each update to avoid duplicates
     * BUG: Continuously appending to the text file - duplications
     * @param tasks - tasklist array from TaskList
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