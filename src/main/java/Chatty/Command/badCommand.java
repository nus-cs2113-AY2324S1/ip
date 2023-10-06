/**
 * The badCommand class represents a bad input and command and what to do next
 */
package Chatty.Command;

import Chatty.Storage;
import Chatty.TaskList;
import Chatty.Ui;
import Chatty.tasks.Task;

public class badCommand extends Command {
    int index;

    public badCommand(String input) {
        super(input);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage("Unknown command. Please try again or type \"help\"");
    }
}