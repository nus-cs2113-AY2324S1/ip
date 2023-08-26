package dawson.commands;

import dawson.Dawson;
import dawson.TaskList;

public class List extends Command {

    private TaskList list;

    public List(TaskList list) {
        this.list = list;
    }

    @Override
    public void execute() {
        String listString = list.toString();
        Dawson.printText(listString, true);
    }
    
}
