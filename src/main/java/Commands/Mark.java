package Commands;

import Data.TaskList;
import Exceptions.CSGPTMissingTaskException;
import Ui.Ui;

/**
 * Command to mark a task as done or not done
 */
public class Mark extends Command {
    private final int taskNumber;
    private final boolean isDone;

    /**
     * Constructor for Mark command
     * @param taskNumber Task number to be marked
     * @param isDone Boolean to indicate if the task is done or not done
     */
    public Mark(int taskNumber, boolean isDone) {
        this.taskNumber = taskNumber;
        this.isDone = isDone;
    }

    /**
     * Executes the command to mark a task as done or not done
     * @param list Tasklist to be modified
     * @param ui Ui to print messages
     * @throws CSGPTMissingTaskException Exception thrown when the task cannot be found
     */
    @Override
    public void execute(TaskList list, Ui ui) throws CSGPTMissingTaskException {
        try {
            list.mark(taskNumber, isDone);
            ui.printText("Consider it done:\n\t" + list.getTask(taskNumber).toString());
        } catch (CSGPTMissingTaskException e) {
            throw new CSGPTMissingTaskException();
        }
    }
}
