package jarvis.storage;

import jarvis.exception.JarvisException;
import jarvis.tasks.Task;
import jarvis.tasklist.TaskList;
import jarvis.tasks.Deadline;
import jarvis.tasks.Todo;
import jarvis.tasks.Event;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
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
     * Write new data to file
     * @param filePath - specify file location where data is written to
     * @param dataToAdd - data to be written
     * @param toAppend - if True, data is added to the back of the existing data
    **/
    private void writeToFile(String filePath, String dataToAdd, boolean toAppend) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, toAppend);
        fileWriter.write(dataToAdd);
        fileWriter.close();
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
        } catch (NullPointerException | IOException invalidFilePath) {
            System.out.println("    " + invalidFilePath.getMessage());
        }
        int taskIndex = 0;
        try {
            Scanner fileScanner = new Scanner(dataFile);
            while (fileScanner.hasNext()) {
                taskIndex++;
                String nextTask = fileScanner.nextLine();
                String[] taskSubStrings = nextTask.split("\\|");
                String taskType = taskSubStrings[0].strip();
                String taskDoneStatus = taskSubStrings[1].strip();
                String taskDescription = taskSubStrings[2].strip();

                switch (taskType) {
                case "T": tasks.addToTaskList("todo " + taskDescription,
                        Task.TaskType.TODO, false);
                    if (taskDoneStatus.equals("done")) {
                        tasks.markTaskAsDone(taskIndex, false);
                    }
                    break;
                case "D":
                    String dueTime = taskSubStrings[3].replace("(by:", "")
                            .replace(")", "").strip();
                    tasks.addToTaskList("deadline " + taskDescription + " /by " + dueTime,
                            Task.TaskType.DEADLINE, false);
                    if (taskDoneStatus.equals("done")) {
                        tasks.markTaskAsDone(taskIndex, false);
                    }
                    break;
                case "E":
                    String[] taskTimings = taskSubStrings[3].strip().split("\\(from:")[1]
                            .split("to:");
                    String startTime = taskTimings[0];
                    String endTime = taskTimings[1].split("\\)")[0];
                    tasks.addToTaskList("event " + taskDescription + " /from " + startTime
                            + " /to " + endTime, Task.TaskType.EVENT, false);
                    if (taskDoneStatus.equals("done")) {
                        tasks.markTaskAsDone(taskIndex, false);
                    }
                    break;
                default:
                    System.out.println(nextTask);
                    break;
                }
            }
        }
        catch(NullPointerException | IOException  invalidFilePath){
            System.out.println("    " + invalidFilePath.getMessage());
        }
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
                        writeToFile(dataFile.getPath(), "T | done |  " + task.getDescription()
                                + System.lineSeparator(), true);
                    } else {
                        writeToFile(dataFile.getPath(), "T | undone |  " + task.getDescription()
                                + System.lineSeparator(), true);
                    }
                    break;
                case DEADLINE:
                    if (task.taskIsDone()) {
                        writeToFile(dataFile.getPath(), "D | done |  " + task.getDescription()
                                + " | " + task.getTime() + System.lineSeparator(), true);
                    } else {
                        writeToFile(dataFile.getPath(), "D | undone |  " + task.getDescription()
                                + " | " + task.getTime() + System.lineSeparator(), true);
                    }
                    break;
                case EVENT:
                    if (task.taskIsDone()) {
                        writeToFile(dataFile.getPath(), "E | done |  " + task.getDescription()
                                + " | " + task.getTime() + System.lineSeparator(), true);
                    } else {
                        writeToFile(dataFile.getPath(), "E | undone |  " + task.getDescription()
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