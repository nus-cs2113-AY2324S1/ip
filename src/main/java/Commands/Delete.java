package Commands;

import Data.TaskList;
import Exceptions.CSGPTMissingTaskException;

import Ui.Ui;

/**
 * Command to delete a task from the list
 */
public class Delete extends Command {
    private final int taskNumber;

    /**
     * Constructor for Delete command
     * @param taskNumber Task number to be deleted
     */
    public Delete(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to delete a task from the list
     * @param list Tasklist to be modified
     * @param ui Ui to print messages
     * @throws CSGPTMissingTaskException Exception thrown when the task cannot be found
     */
    @Override
    public void execute(TaskList list, Ui ui) throws CSGPTMissingTaskException {
        try {
            list.delete(taskNumber);
            ui.printText("Noted. I've removed this task. Now you have " + list.size() + " tasks in the list.");
        } catch (CSGPTMissingTaskException e) {
            throw new CSGPTMissingTaskException();
        }
    }
}
