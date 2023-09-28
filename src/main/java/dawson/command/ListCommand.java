package dawson.command;

import dawson.Dawson;
import dawson.TaskList;

public class ListCommand extends Command {

    private TaskList list;

    public ListCommand(TaskList list) {
        this.list = list;
    }

    @Override
    public void execute() {
        String[] listString = list.getTaskStrings();
        Dawson.printText(listString);
    }
    
}
