package data;

import exception.SimonException;
import task.*;

import static data.DataMethods.*;
import java.util.ArrayList;

import static data.Loader.readToList;
import static data.SimonFilePath.simontxtFilePath;

/**
 * The Storage class handles file storage operations for a task management application.
 * It allows loading, creating, marking, unmarking, deleting, and adding tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new Storage instance with the specified file path.
     *
     * @param filePath The path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws SimonException If the file does not exist or an error occurs while reading.
     */
    public ArrayList<Task> load() throws SimonException {
        if (!isFileExist(filePath)) {
            throw new SimonException();
        }
        return readToList(filePath);
    }

    /**
     * Creates a "simon.txt" file in the specified user directory.
     *
     * @param userDirectory The user's directory where the "simon.txt" file will be created.
     */
    public void createSimonTxt(String userDirectory) {
        String dataDirectory = userDirectory + "/" + "data";
        createDirectory(dataDirectory);
        createFileInDirectory(dataDirectory, "simon.txt");
    }

    /**
     * Marks a task as done by updating its status in the file.
     *
     * @param taskNumber The task number to mark as done.
     * @param taskList   The list of tasks to update.
     */
    public void markTask(String taskNumber, ArrayList<Task> taskList) {
        // Convert task number to element in tasks array
        int target = Integer.parseInt(taskNumber) - 1;
        try {
            Task task = taskList.get(target);
            String newText = task.toText();
            editTextFile(filePath, newText, target + 1);
        } catch (IndexOutOfBoundsException ignored) {

        }
    }

    /**
     * Unmarks a previously marked task by updating its status in the file.
     *
     * @param taskNumber The task number to unmark.
     * @param taskList   The list of tasks to update.
     */
    public void unmarkTask(String taskNumber, ArrayList<Task> taskList) {
        // Convert task number to element in tasks array
        int target = Integer.parseInt(taskNumber) - 1;
        try {
            String newText = taskList.get(target).toText();
            editTextFile(filePath, newText, target + 1);
        } catch (IndexOutOfBoundsException ignored) {

        }
    }

    /**
     * Deletes a task from the file and updates the remaining task numbers.
     *
     * @param taskNumber The task number to delete.
     * @param taskList   The list of tasks to update.
     */
    public void deleteTask(String taskNumber, ArrayList<Task> taskList) {
        int target = Integer.parseInt(taskNumber) - 1;
        try {
            for (int i = target; i < Task.getNumberOfTask(); i++) {
                String newText = taskList.get(i).toText();
                editTextFile(filePath, newText, i + 1);
            }
            deleteLineFromFile(filePath, Task.getNumberOfTask());
        } catch (IndexOutOfBoundsException ignored) {

        }
    }

    /**
     * Adds a new todo task to the file.
     *
     * @param description The description of the todo task.
     * @param taskList    The list of tasks to update.
     * @throws SimonException If the description is empty.
     */
    public void addTodo(String description, ArrayList<Task> taskList) throws SimonException {
        String[] splitDescriptions = description.split(" ");
        if (splitDescriptions.length == 0 || splitDescriptions[0].isEmpty()) {
            throw new SimonException();
        }

        addTextToFile(filePath, taskList.get(Task.getNumberOfTask() - 1).toText());
    }

    /**
     * Adds a new event task to the file.
     *
     * @param event     The event task description and details.
     * @param taskList  The list of tasks to update.
     * @throws SimonException If the description is empty.
     */
    public void addEvent(String event, ArrayList<Task> taskList) throws SimonException {
        try {
            // Split between 'description' and '/from and /to'
            String[] splitElements = event.split(" /from ", 2);
            String description = splitElements[0];

            String[] splitDescriptions = description.split(" ");
            if (splitDescriptions.length == 0 || splitDescriptions[0].isEmpty()) {
                throw new SimonException();
            }

            addTextToFile(filePath, taskList.get(Task.getNumberOfTask() - 1).toText());
        } catch (IndexOutOfBoundsException ignored) {

        }
    }

    /**
     * Adds a new deadline task to the file.
     *
     * @param deadline  The deadline task description and due date.
     * @param taskList  The list of tasks to update.
     * @throws SimonException If the description is empty.
     */
    public void addDeadline(String deadline, ArrayList<Task> taskList) throws SimonException {
        try {
            // Split between 'description' and '/by'
            String[] splitElements = deadline.split(" /by ", 2);
            String description = splitElements[0];

            String[] splitDescriptions = description.split(" ");
            if (splitDescriptions.length == 0 || splitDescriptions[0].isEmpty()) {
                throw new SimonException();
            }

            addTextToFile(filePath, taskList.get(Task.getNumberOfTask() - 1).toText());
        } catch (IndexOutOfBoundsException ignored) {

        }
    }
}

