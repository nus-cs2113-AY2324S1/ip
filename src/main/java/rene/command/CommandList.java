package rene.command;

import rene.storage.Storage;
import rene.tasklist.TaskList;
import rene.ui.Ui;

public class CommandList extends Command {
    public CommandList(){
        super(CommandType.LIST);
    }
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        tasks.printTaskList();
    }
}
