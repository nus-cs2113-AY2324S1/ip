package command;

import duke.Duke;
import message.Text;
import task.Task;

import java.io.IOException;

import static commandFormat.CommandFormat.getTaskNo;
import static fileIO.FileIO.overwriteToFile;


public class DeleteCommand extends Command{

    private final String index;

    public DeleteCommand(String index) {
        super(false);
        this.index = index;
    }

    @Override
    public void executeCommand() throws IOException {
        try {
            int deleteIndex = getTaskNo(this.index);
            deleteTask(deleteIndex);
        } catch (NumberFormatException nfe) {
            System.out.println("Hey, please input your command with the correct task number.");
        }
    }

    private static void deleteTask(int deleteIndex) throws IOException{
        if (deleteIndex <= 0 || deleteIndex> Duke.taskCount){
            System.out.println("Oh, No! invalid index! You don't have that task. Please try again.");
            return;
        }
        Text.deleteTaskSuccessMsg(deleteIndex);
        Duke.list = Task.updatedTaskList(deleteIndex - 1);
        overwriteToFile("data/taskList.txt", Task.getConcatenateTasks());
    }

}
