package rene.command;

import rene.storage.Storage;
import rene.tasklist.TaskList;
import rene.ui.Ui;
/**
 * Represents a command to mark a task as done.
 */
public class CommandMark extends Command {
    private int taskIndex;
    /**
     * Creates a new mark command for a task of specified index.
     *
     * @param taskIndex Index of the task to be marked.
     */
    public CommandMark(int taskIndex){
        super(CommandType.MARK);
        this.taskIndex = taskIndex;
    }
    /**
     * Checks if specified task exists.
     * Marks the specified task as done and updates storage data.
     *
     * @param ui User interface for interactions with user through CLI.
     * @param tasks Current task list in the program.
     * @param dataStorage Hard disk storage for storing task data.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        String taskName = tasks.viewTaskByIndex(taskIndex);
        if(!taskName.equals("Task Not Found")) {
            tasks.markTaskAsDone(taskIndex, true);
            dataStorage.updateData(tasks);
        }
    }
}
