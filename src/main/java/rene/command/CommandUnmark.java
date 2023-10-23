package rene.command;

import rene.storage.Storage;
import rene.tasklist.TaskList;
import rene.ui.Ui;
/**
 * Represents a command to mark a task as not done.
 */
public class CommandUnmark extends Command {
    private int taskIndex;
    /**
     * Creates a new unmark command for a task of specified index.
     *
     * @param taskIndex Index of the task to be unmarked.
     */
    public CommandUnmark(int taskIndex){
        super(CommandType.UNMARK);
        this.taskIndex = taskIndex;
    }
    /**
     * Checks if specified task exists.
     * Marks the specified task as not done and updates storage data.
     *
     * @param ui User interface for interactions with user through CLI.
     * @param tasks Current task list in the program.
     * @param dataStorage Hard disk storage for storing task data.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        String taskName = tasks.viewTaskByIndex(taskIndex);
        if(!taskName.equals("Task Not Found")) {
            tasks.markTaskAsNotDone(taskIndex);
            dataStorage.updateData(tasks);
        }
    }
}
