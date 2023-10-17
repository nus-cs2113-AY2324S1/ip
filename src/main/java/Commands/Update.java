package Commands;

import Exceptions.KenMissingTaskException;
import Tasks.TaskList;
import UI.Ui;

/**
 * Represents a command to manage the status of a task in the Barbie-themed task manager.
 */
public class Update extends Command {
    private final int taskNumber;
    private final boolean isDone;

    /**
     * Constructor for "Update" command with the specified task number and status of task.
     *
     * @param taskNumber The number of the task to be updated.
     * @param isDone The new status of the task (true if completed and false if not completed).
     */
    public Update(int taskNumber, boolean isDone) {
        this.taskNumber = taskNumber;
        this.isDone = isDone;
    }

    /**
     * Executes the "Update" command by updating the status of the specified task and printing messages to the user.
     *
     * @param list The task list on which the command should operate.
     * @throws KenMissingTaskException If the task specified by the taskNumber is not found.
     */
    @Override
    public void run(TaskList list) throws KenMissingTaskException{
        try {
            list.updateStatus(taskNumber - 1, isDone);

            String text;
            text = isDone ? "Barbie-tastic! You've completed this task with glamour!"
                    : "Back to the runway, darling! This task needs more Barbie magic!";
            Ui.printTexts(new String[]{
                    text,
                    list.getTask(taskNumber - 1).toString()
            });
        } catch (KenMissingTaskException e) {
            throw new KenMissingTaskException();
        }
    }
}
