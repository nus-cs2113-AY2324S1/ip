package Duke.Storage;

import Duke.Command.Command;
import Duke.Duke;
import Duke.Exception.InvalidDateTimeSpecifiedException;
import Duke.Exception.NoTaskSpecifiedException;
import Duke.Task.Task;
import Duke.Task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
    public static final String TASK_LOADING_FAILED_MESSAGE = "Failed loading task:\n %s\n It will be removed.\n";

    private final File file;
    private final File directory;
    public Storage(String dirPath, String filePath) {
        file = new File(filePath);
        directory = new File(dirPath);
    }

    /**
     * Saves all the tasks from the taskList to the given filePath.
     *
     * @param taskList A TaskList object that contains Task objects.
     * @throws IOException if the file is unable to be opened by this program.
     */
    public void saveTaskList(TaskList taskList) throws IOException {
        String taskSaveFormat;
        FileWriter fw = new FileWriter(file);

        for (int i = 1; i < taskList.getNumTask() + 1; i++) {
            taskSaveFormat = taskList.getTask(i).convertToSaveFormat();
            fw.write(taskSaveFormat + "\n");
        }
        fw.close();
    }

    /**
     * This function verifies if the filename for this Storage Class exists.
     *
     * @return True or False depend on whether the file is present.
     */
    public boolean verifyStorageFilePresent() {
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
     * @param taskList TaskList to load the tasks from file data into.
     */
    public void loadTaskList(TaskList taskList) {

        Scanner s = initialiseFileScanner();

        while (s.hasNext()) {
            String storedMessage = s.nextLine();
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


    private Scanner initialiseFileScanner() {
        Scanner s;
        try {
            s = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return s;
    }
}
