package fredbot.commands;

import fredbot.Storage;
import fredbot.TaskList;
import fredbot.Ui;

import java.io.IOException;

public class MarkCommand extends Command{
    private int index;
    private boolean mark = false;

    public MarkCommand(int index, boolean mark) {
        this.index = index;
        this.mark = mark;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.markTask(index, mark);
        ui.printMarkTask(tasks.getTask(index));
        try {
            storage.addTaskstoFile(tasks);
        } catch (IOException e) {
            System.out.println("unable to add to file");
        }
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }
}
