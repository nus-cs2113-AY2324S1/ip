package fredbot.commands;

import fredbot.Storage;
import fredbot.TaskList;
import fredbot.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.printRemoveTask(tasks.removeTask(index));
        try {
            storage.addTaskstoFile(tasks);
        } catch (IOException e) {
            System.out.println("unable to add to file");
        }
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
