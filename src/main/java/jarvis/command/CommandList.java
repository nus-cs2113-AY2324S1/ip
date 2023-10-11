package jarvis.command;
import jarvis.storage.Storage;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;
/**
 * Command to List the tasklist
 */
public class CommandList extends Command {

    public CommandList(){
        super(CommandType.LIST);
    }
    /**
     * Overrides the executeCommand method from Command class to define the
     * execution for the "list" command.
     *
     * @param ui         The Ui object, to interact with the user.
     * @param dataStorage Not used in this method but provided because it's declared abstract in the superclass.
     * @param tasks       The TaskList object that manages the tasks.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        tasks.printTaskList();
    }
}
