package command;

import duke.Duke;
import task.Task;

import java.io.IOException;

import static commandFormat.CommandFormat.getTaskNo;
import static fileIO.FileIO.overwriteToFile;

public class MarkCommand extends Command {

    private String index;

    /**
     *
     * @param index points to the target task
     */
    public MarkCommand(String index) {
        super(false);
        this.index = index;
    }

    /**
     * overwriteFile update the whole saved file with updated content, i.e. one task marked
     */
    @Override
    public void executeCommand(){
        try {
            int taskNo = getTaskNo(this.index);
            Duke.list[taskNo - 1].setDone(taskNo, Duke.taskCount, Duke.list);
            overwriteToFile("data/taskList.txt", Task.getConcatenateTasks());
        } catch (NumberFormatException nfe) {
            System.out.println("Hey, please input your command with the correct task number.");
        } catch (IOException e) {
            System.out.println("Oh NO! Failed to find save file!");
        }
    }

}
