package rene.command;

import rene.storage.Storage;
import rene.task.Task;
import rene.tasklist.TaskList;
import rene.ui.Ui;
/**
 * Represents a command to add an event task.
 */
public class CommandEvent extends Command{
    private String userInput;
    /**
     * Creates a new event command to add an event task.
     *
     * @param userInput User input containing details of the event task.
     */
    public CommandEvent(String userInput){
        super(CommandType.EVENT);
        this.userInput = userInput;
    }
    /**
     * Adds the event task and updates storage data.
     *
     * @param ui User interface for interactions with user through CLI.
     * @param tasks Current task list in the program.
     * @param dataStorage Hard disk storage for storing task data.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        tasks.addToTaskList(userInput, Task.TaskType.EVENT, true);
        dataStorage.updateData(tasks);
    }
}
