package fredbot.commands;

import fredbot.Storage;
import fredbot.TaskList;

public class DeleteCommand extends Command {
    private int index = Integer.parseInt(null);

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        if (Integer.parseInt(null) == index) {
            System.out.println("No task found");
            return;
        }
        tasks.removeTask(index);
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
