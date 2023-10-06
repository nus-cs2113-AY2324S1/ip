package command;

import duke.Duke;

import static commandFormat.CommandFormat.getTaskNo;

public class UnmarkCommand extends Command{

    private String index;

    public UnmarkCommand(String index) {
        super(false);
        this.index = index;
    }

    @Override
    public void executeCommand() {
        try {
            int taskNo = getTaskNo(this.index);
            Duke.list[taskNo - 1].setNotDone(taskNo, Duke.taskCount, Duke.list);
        } catch (NumberFormatException nfe) {
            System.out.println("Hey, please input your command with the correct task number.");
        }
    }
}
