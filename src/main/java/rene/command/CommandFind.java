package rene.command;

import rene.storage.Storage;
import rene.tasklist.TaskList;
import rene.ui.Ui;

public class CommandFind extends Command{
    private String searchDetails;

    public CommandFind(String searchDetails){
        super((CommandType.FIND));
        this.searchDetails = searchDetails;
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks) {
        tasks.searchList(searchDetails);
    }
}
