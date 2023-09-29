package Duke.Storage;

import Duke.Command.Command;
import Duke.Exception.NoDateTimeSpecifiedException;
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

    public Storage(){

    }

    /**
     * This function verifies if the file of a given filename exists
     *
     * @param directory directory that contains the file
     * @param file file object of the filename.
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
                System.out.println("Unable to create folder " + directory);
            }
        }
        return false;
    }

    /**
     * Loads data from the file with a scanner pointer and place it in a TaskList
     * @param scanner Pointer to the file data.
     * @param taskList TaskList to load the tasks from file data into.
     */
    public void loadTaskList(Scanner scanner, TaskList taskList) {
        while (scanner.hasNext()) {
            String storedMessage = scanner.nextLine();
            String[] messageFragments = storedMessage.split("\\|");
            Task task = null;
            switch (messageFragments[0].trim()) {
            case ("T"):
                try {
                    task = Command.createToDo(messageFragments[2]);
                } catch (NoTaskSpecifiedException e) {
                    System.out.println("Failed loading Todo. Todo will be deleted.");
                }
                break;
            case ("D"):
                try {
                    task = Command.createDeadline(messageFragments[2]);
                } catch (NoTaskSpecifiedException | NoDateTimeSpecifiedException e) {
                    System.out.println("Failed loading Deadline. Deadline will be deleted.");
                }
                break;
            case ("E"):
                try {
                    task = Command.createEvent(messageFragments[2]);
                } catch (NoTaskSpecifiedException | NoDateTimeSpecifiedException e) {
                    System.out.println("Failed loading Event. Event will be deleted.");
                }
                break;
            default:
                continue;
            }
            if (task != null) {
                Command.addTaskToTaskList(task, taskList);
            }
            if (messageFragments[1].trim().equals("1")) {
                taskList.getTask(taskList.getNumTask() - 1).setDone();
            }
        }
    }

}
