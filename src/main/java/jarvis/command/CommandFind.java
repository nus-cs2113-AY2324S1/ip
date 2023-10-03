package jarvis.command;
import jarvis.storage.Storage;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;

public class CommandFind extends Command {
    private final String searchIndex;

    public CommandFind(String searchIndex){
        super(CommandType.FIND);
        this.searchIndex = searchIndex;
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        tasks.searchList(searchIndex);
    }
}
