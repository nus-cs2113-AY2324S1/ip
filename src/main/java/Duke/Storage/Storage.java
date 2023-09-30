package Duke.Storage;

import Duke.Command.Command;
import Duke.Exception.InvalidDateTimeSpecifiedException;
import Duke.Exception.NoTaskSpecifiedException;
import Duke.Task.Task;
import Duke.Task.TaskList;

import java.io.File;
import java.util.Scanner;

/**
 * Storage class deals with the saving TaskList to the file and
 * retrieving the TaskList from the file.
 */
public class Storage {

    public static final String DIRECTORY_CREATION_ERROR_PROMPT = "Unable to create folder %s";
    public static final String TODO_SYMBOL = "T";
    public static final String DEADLINE_SYMBOL = "D";
    public static final String EVENT_SYMBOL = "E";
    public static final String TASK_LOADING_FAILED_MESSAGE = "Failed loading task: %s. It will be removed.";

    public Storage() {

    }

    /**
     * This function verifies if the file of a given filename exists
     *
     * @param directory directory that contains the file
     * @param file      file object of the filename.
     * @return True or False depend on whether the file is present.
     */
    public boolean verifyStorageFilePresent(File directory, File file) {
        boolean isDirectoryPresent = directory.exists() && directory.isDirectory();
        if (file.exists()) {
            return true;
        } else if (!isDirectoryPresent) {
            try {
                directory.mkdir();
            } catch (Exception e) {
                System.out.printf(DIRECTORY_CREATION_ERROR_PROMPT, directory);
            }
        }
        return false;
    }

    /**
     * Loads data from the file with a scanner pointer and place it in a TaskList
     *
     * @param scanner  Pointer to the file data.
     * @param taskList TaskList to load the tasks from file data into.
     */
    public void loadTaskList(Scanner scanner, TaskList taskList) {
        while (scanner.hasNext()) {
            String storedMessage = scanner.nextLine();
            String[] messageFragments = storedMessage.split("\\|");
            Task task;
            try {
                switch (messageFragments[0].trim()) {

                case TODO_SYMBOL:
                    task = Command.createToDo(messageFragments[2]);
                    break;
                case DEADLINE_SYMBOL:
                    task = Command.createDeadline(messageFragments[2]);
                    break;
                case EVENT_SYMBOL:
                    task = Command.createEvent(messageFragments[2]);
                    break;
                default:
                    continue;
                }

                Command.addTaskToTaskList(task, taskList);

                if (messageFragments[1].trim().equals("1")) {
                    taskList.getTask(taskList.getNumTask()).setDone();
                }
            } catch (NoTaskSpecifiedException | InvalidDateTimeSpecifiedException e) {
                System.out.printf(TASK_LOADING_FAILED_MESSAGE, storedMessage);
            }
        }
    }
}
