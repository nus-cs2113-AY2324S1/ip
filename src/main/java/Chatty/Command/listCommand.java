/**
 * The listCommand class represents a command to list all tasks and its execution
 */
package Chatty.Command;

import Chatty.Storage;
import Chatty.TaskList;
import Chatty.Ui;

public class listCommand extends Command{

    public listCommand(String input){
        super(input);
    }

    public void execute (TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.printMessage((i + 1) + ". " + tasks.get(i).getDescription());
        }
    }
}
