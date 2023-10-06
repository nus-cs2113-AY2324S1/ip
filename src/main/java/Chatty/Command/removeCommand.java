package Chatty.Command;

import Chatty.Storage;
import Chatty.TaskList;
import Chatty.Ui;
import Chatty.tasks.Task;

public class removeCommand extends Command {
    String input;
    int index;

    public removeCommand(String input) {
        super(input);
        this.index = Integer.parseInt(input.substring(7)) - 1;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.get(index);
        tasks.deleteTask(index);
        ui.printMessage("Noted. I've removed this task:");
        ui.printMessage(deletedTask.getDescription());
    }

}