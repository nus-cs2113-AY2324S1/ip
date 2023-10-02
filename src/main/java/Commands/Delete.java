package Commands;

import Exceptions.KenMissingTaskException;
import Tasks.TaskList;
import UI.Ui;

/**
 * Represents a command to delete a task from TaskList in the Barbie-themed task manager.
 */
public class Delete extends Command{
    private final int taskNumber;

    /**
     * Constructor for "Delete" command with the specified task number to be deleted.
     *
     * @param taskNumber The number of the task to be deleted from the task list.
     */
    public Delete(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the "Delete" command by deleting the specified task from TaskList and printing messages to the user.
     *
     * @param list The task list on which the command should operate.
     * @throws KenMissingTaskException If the task specified by the task number is not found or does not exist.
     */
    @Override
    public void run(TaskList list) throws KenMissingTaskException {
        try {
            String taskText = list.getTask(taskNumber - 1).toString();
            list.deleteTask(taskNumber - 1);
            Ui.printTexts(new String[] {
                    "Noted, darling! I've given that task the boot, just like a fashion faux pas!",
                    taskText,
                    "Now your list is sparkling with " + list.getSize() + " glamorous tasks, darling!"
            });
        } catch (KenMissingTaskException e) {
            throw new KenMissingTaskException();
        }
    }
}
