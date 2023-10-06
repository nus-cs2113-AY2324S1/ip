package command;

import duke.Duke;
import task.Task;

import java.io.IOException;

import static commandFormat.CommandFormat.getTaskNo;
import static fileIO.FileIO.overwriteToFile;

public class UnmarkCommand extends Command{
    /**
     * similar to mark
     */
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
            overwriteToFile("data/taskList.txt", Task.getConcatenateTasks());
        } catch (NumberFormatException nfe) {
            System.out.println("Hey, please input your command with the correct task number.");
        } catch (IOException e) {
            System.out.println("Oh NO! Failed to find save file!");
        }
    }
}
