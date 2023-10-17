package Tasks;

import Exceptions.KenMissingTaskException;
import UI.Ui;

import java.util.ArrayList;


/**
 * Represents a list of tasks in the Barbie-themed task manager.
 */
public class TaskList {
    private final ArrayList<Task> list;

    /**
     * Initializes a new task list.
     */
    public TaskList() {
        list = new ArrayList<Task>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Retrieve task from the task list  by its index.
     *
     * @param taskNumber The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws KenMissingTaskException If the specified task index is out of bounds.
     */
    public Task getTask(int taskNumber) throws KenMissingTaskException {
        try {
            return list.get(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new KenMissingTaskException();
        }
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Updates the status of a task in the TaskList.
     *
     * @param taskNumber The index of the task to update.
     * @param status The new status of the task.
     * @throws KenMissingTaskException If the specified task index is out of bounds.
     */
    public void updateStatus(int taskNumber, boolean status) throws KenMissingTaskException {
        try {
            list.get(taskNumber).setDone(status);
        } catch (IndexOutOfBoundsException e) {
            throw new KenMissingTaskException();
        }
    }

    /**
     * Retrieves all the task from the TaskList and prints them to the CLI.
     */
    public void getTasks() {
        int taskSize = list.size();
        String[] texts = new String[taskSize + 1];
        texts[0] = "Behold, your list of enchanting tasks!";
        for (int i = 1; i <= taskSize; i++) {
            Task currentTask = list.get(i - 1);
            texts[i] = "\t" + i + "." + currentTask.toString();
        }
        Ui.printTexts(texts);
    }

    /**
     * Retrieves tasks containing a specific keyword from the TaskList and prints them to the CLI.
     *
     * @param keyword The keyword to search for within task descriptions.
     */
    public void getTasks(String keyword) {
        ArrayList<String> foundList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Task currentTask = list.get(i);
            String taskDescription = currentTask.toString();
            if (taskDescription.contains(keyword)) {
                foundList.add("\t" + (foundList.size() + 1) + "." + currentTask.toString());
            }
        }

        String openingLine;
        if (foundList.isEmpty()) {
            openingLine = "Oh, sugarplum! You have no matching tasks with '" + keyword + "'";
        } else {
            openingLine = "These are the tasks that match the keyword, hun!";
        }
        Ui.printTexts(openingLine, foundList);
    }

    /**
     * Deletes a task from the TaskList by its index.
     *
     * @param taskNumber The index of the task to delete.
     * @throws KenMissingTaskException If the specified task index is out of bounds.
     */
    public void deleteTask(int taskNumber) throws KenMissingTaskException{
        try {
            list.remove(taskNumber);
        } catch(IndexOutOfBoundsException e) {
            throw new KenMissingTaskException();
        }
    }
}
