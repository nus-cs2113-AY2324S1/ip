package command;

import Oriento.Oriento;
import task.Task;

import java.io.IOException;

import static commandFormat.CommandFormat.getTaskNo;
import static fileIO.FileIO.overwriteToFile;

public class UnmarkCommand extends Command{

    private String index;

    /**
     * similar to mark
     */
    public UnmarkCommand(String index) {
        super(false);
        this.index = index;
    }

    /**
     * change the task statue to unfinished
     * only applicable to finished task
     * It gives error message when wrong index is used or the output file cannot be found
     */
    @Override
    public void executeCommand() {
        try {
            int taskNo = getTaskNo(this.index);
            Oriento.list[taskNo - 1].setNotDone(taskNo, Oriento.taskCount, Oriento.list);
            overwriteToFile("data/taskList.txt", Task.getConcatenateTasks());
        } catch (NumberFormatException nfe) {
            System.out.println("Hey, please input your command with the correct task number.");
        } catch (IOException e) {
            System.out.println("Oh NO! Failed to find save file!");
        }
    }
}
