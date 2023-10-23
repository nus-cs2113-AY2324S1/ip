package rene.command;

import rene.storage.Storage;
import rene.task.Task;
import rene.tasklist.TaskList;
import rene.ui.Ui;
/**
 * Represents a command to add a deadline task.
 */
public class CommandDeadline extends Command {
    private String userInput;
    /**
     * Creates a new deadline command to add a deadline task.
     *
     * @param userInput User input containing details of the deadline task.
     */
    public CommandDeadline(String userInput){
        super(CommandType.DEADLINE);
        this.userInput = userInput;
    }
    /**
     * Adds the deadline task and updates storage data.
     *
     * @param ui User interface for interactions with user through CLI.
     * @param tasks Current task list in the program.
     * @param dataStorage Hard disk storage for storing task data.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        tasks.addToTaskList(userInput, Task.TaskType.DEADLINE, true);
        dataStorage.updateData(tasks);
    }
}
