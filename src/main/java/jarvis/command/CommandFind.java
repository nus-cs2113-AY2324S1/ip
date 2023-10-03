package jarvis.command;
import jarvis.storage.Storage;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;
/**
 * Command to add a Find a task
 */
public class CommandFind extends Command {
    private final String searchIndex;
    /**
     * Constructor to initialize a CommandFind object.
     *
     * @param searchIndex A string containing the keyword/index to be used to find tasks.
     */
    public CommandFind(String searchIndex){
        super(CommandType.FIND);
        this.searchIndex = searchIndex;
    }
    /**
     * Overrides the executeCommand method from Command class. This method executes the
     * specific actions to find a task.
     *
     * @param ui         The Ui object to interact with the user.
     * @param dataStorage Not used in this method but provided because it's declared abstract in the superclass.
     * @param tasks       The TaskList object that holds and manages the tasks.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        tasks.searchList(searchIndex);
    }
}
