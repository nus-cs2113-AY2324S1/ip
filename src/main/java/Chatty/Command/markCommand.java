package Chatty.Command;

import Chatty.Storage;
import Chatty.TaskList;
import Chatty.Ui;
import Chatty.tasks.Task;

public class markCommand extends Command {
    int index;
    boolean isDone;

    public markCommand(String input, boolean isDone) {
        super(input);
        this.index = Integer.parseInt(input.substring(5)) - 1;
        this.isDone = isDone;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTask(index, isDone);
        ui.printMessage(Ui.LINE);
        if (isDone){
            ui.printMessage("Nice! I've marked this task as done:");
            ui.printMessage(tasks.get(index).getDescription());
        } else {
            ui.printMessage("OK, I've marked this task as not done yet:");
            ui.printMessage(tasks.get(index).getDescription());
        }
    }
}