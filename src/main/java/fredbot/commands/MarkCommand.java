package fredbot.commands;

import fredbot.Storage;
import fredbot.TaskList;

public class MarkCommand extends Command{
    private int index = Integer.parseInt(null);
    private boolean mark = false;

    public MarkCommand(int index, boolean mark) {
        this.index = index;
        this.mark = mark;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        if (Integer.parseInt(null) == index) {
            System.out.println("No task found");
            return;
        }
        tasks.markTask(index, mark);
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }
}
