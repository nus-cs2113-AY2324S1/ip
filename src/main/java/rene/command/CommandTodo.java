package rene.command;

import rene.storage.Storage;
import rene.task.Task;
import rene.tasklist.TaskList;
import rene.ui.Ui;
/**
 * Represents a command to add a todo task.
 */
public class CommandTodo extends Command {

    private String userInput;
    /**
     * Creates a new todo command to add a todo task.
     *
     * @param userInput User input containing details of the todo task.
     */
    public CommandTodo(String userInput){
        super(CommandType.TODO);
        this.userInput = userInput;
    }
    /**
     * Adds the todo task and updates storage data.
     *
     * @param ui User interface for interactions with user through CLI.
     * @param tasks Current task list in the program.
     * @param dataStorage Hard disk storage for storing task data.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        tasks.addToTaskList(userInput, Task.TaskType.TODO, true);
        dataStorage.updateData(tasks);
    }

}
