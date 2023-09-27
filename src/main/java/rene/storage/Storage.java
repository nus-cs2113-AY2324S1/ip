package rene.storage;
import rene.task.Task;
import rene.tasklist.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Represents the hard disk storage where
 * task data is stored, read and updated.
 */
public class Storage {
    private File dataFile;
    /**
     * Creates a new storage for storing task data.
     *
     * @param filePath The file location in hard disk where data is stored and read from.
     */
    public Storage(String filePath) {
        dataFile = new File(filePath);
    }
    /**
     * Write in new data to storage.
     *
     * @param filePath The file location in hard disk where data is written to.
     * @param textToAdd Data to be written.
     * @param toAppend If true, new data is added to the back of existing data instead of overwriting them.
     */
    private void writeToFile(String filePath, String textToAdd, boolean toAppend) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, toAppend);
        fileWriter.write(textToAdd);
        fileWriter.close();
    }
    /**
     * Build a new task list from data stored in hard disk.
     * Used at program start to build the current task list.
     *
     * @param tasks The task list to be built.
     */
    public void buildCurrentListFromFile(TaskList tasks){
        try {
            if (dataFile.createNewFile()) {
                System.out.println("    Task-list created: " + dataFile.getName());
            }
        } catch(NullPointerException | IOException invalidFilePath){
            System.out.println("    " + invalidFilePath.getMessage());
        }
        int taskIndex = 0;
        try {
            Scanner fileScanner = new Scanner(dataFile);
            if (fileScanner.hasNext()) {
                fileScanner.nextLine();
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
        }
        catch(NullPointerException | IOException  invalidFilePath){
            System.out.println("    " + invalidFilePath.getMessage());
        }
    }
    /**
     * Build a new task list from data stored in hard disk.
     * Prints out the tasks in the list in CLI.
     *
     * @param tasks The task list that has been built.
     */
    public void loadData(TaskList tasks) {
        buildCurrentListFromFile(tasks);
        if (tasks.getTaskListSize() > 0) {
            System.out.println("    You currently have the following tasks uWu");
            tasks.printTaskList();
        } else {
            System.out.println("    You currently have no saved tasks uWu");
        }
    }
    /**
     * Overwrites all existing data in storage with
     * the current tasks in the task list.
     * Used after every task change and on program termination.
     *
     * @param tasks The task list to overwrite current data with.
     */
    public void updateData(TaskList tasks){
        try{
            //flush all current records
            writeToFile(dataFile.getPath(), "Latest Tasks" + System.lineSeparator(), false);
            ArrayList<Task> allTasks = tasks.getAllTasks();
            for (Task task: allTasks) {
                switch (task.getTaskType()) {
                    case TODO:
                        if (task.taskIsDone()) {
                            writeToFile(dataFile.getPath(), "T | done |  " + task.getTaskDescription()
                                    + System.lineSeparator(), true);
                        } else {
                            writeToFile(dataFile.getPath(), "T | undone |  " + task.getTaskDescription()
                                    + System.lineSeparator(), true);
                        }
                        break;
                    case DEADLINE:
                        if (task.taskIsDone()) {
                            writeToFile(dataFile.getPath(), "D | done |  " + task.getTaskDescription()
                                    + " | "  + task.getTaskTiming(true)
                                    + System.lineSeparator(), true);
                        } else {
                            writeToFile(dataFile.getPath(), "D | undone |  " + task.getTaskDescription()
                                    + " | "  + task.getTaskTiming(true)
                                    + System.lineSeparator(), true);
                        }
                        break;
                    case EVENT:
                        if (task.taskIsDone()) {
                            writeToFile(dataFile.getPath(), "E | done |  " + task.getTaskDescription()
                                    + " | "  + task.getTaskTiming(true)
                                    + System.lineSeparator(), true);
                        } else {
                            writeToFile(dataFile.getPath(), "E | undone |  " + task.getTaskDescription()
                                    + " | "  + task.getTaskTiming(true)
                                    + System.lineSeparator(), true);
                        }
                        break;
                }
            }

        }
        catch(NullPointerException | IOException invalidFilePath){
            System.out.println("    " + invalidFilePath.getMessage());
            System.out.println("    ____________________________________________________________");
        }
    }
}
