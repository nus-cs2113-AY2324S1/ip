package jarvis.command;

import jarvis.storage.Storage;
import jarvis.tasks.Task;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;
/**
 * Command to add a ToDo task
 */
public class CommandToDo extends Command {
    private final String userInput;
    /**
     * Constructs a new 'CommandToDo' object.
     *
     * @param userInput The input string provided by the user, used to construct a ToDo task.
     */
    public CommandToDo(String userInput){
        super(CommandType.TODO);
        this.userInput = userInput;
    }
    /**
     * Executes the ToDo task addition command.
     *
     * Adds a ToDo task to the task list using user input and ensures that the task data is
     * updated in persistent storage. It uses the 'addToTaskList' method from 'TaskList' to
     * add a new task and the 'updateFile' method from 'Storage' to update the storage file.
     *
     * @param ui          The 'Ui' object to interact with the user interface.
     * @param dataStorage The 'Storage' object to read/write task data from/to storage.
     * @param tasks       The 'TaskList' object to manipulate and hold the task data.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        tasks.addToTaskList(userInput, Task.TaskType.TODO, true);
        dataStorage.updateFile(tasks);
    }

}
