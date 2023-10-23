package rene.command;

import rene.storage.Storage;
import rene.tasklist.TaskList;
import rene.ui.Ui;
/**
 * Represents a command to delete a task.
 */
public class CommandDelete extends Command {
    private int taskIndex;
    /**
     * Creates a new delete command for a task of specified index.
     *
     * @param taskIndex Index of the task to be deleted.
     */
    public CommandDelete(int taskIndex){
        super(CommandType.DELETE);
        this.taskIndex = taskIndex;
    }
    /**
     * Checks if specified task exists.
     * Deletes the specified task and updates storage data.
     *
     * @param ui User interface for interactions with user through CLI.
     * @param tasks Current task list in the program.
     * @param dataStorage Hard disk storage for storing task data.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        String taskName = tasks.viewTaskByIndex(taskIndex);
        if(!taskName.equals("Task Not Found")) {
            tasks.deleteTaskByIndex(taskIndex);
            dataStorage.updateData(tasks);
        }
    }
}
