package jarvis.command;
import jarvis.storage.Storage;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;

public class CommandList extends Command {

    public CommandList(){
        super(CommandType.LIST);
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        tasks.printTaskList();
    }
}
