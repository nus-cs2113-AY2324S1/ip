package commands;

import ascii.AsciiArt;
import main.Parser;
import task.Task;
/**
 * Represents a list Command to list out the tasks in the task list
 */
public class ListCommand extends Command {
    @Override
    public void execute(String statement, Parser processor) throws IllegalArgumentException {
        if (!statement.isEmpty()){
            throw new IllegalArgumentException ("uwu dont write anything after 'list' " + AsciiArt.getArt("cry"));
        }
        if (processor.taskList.isEmpty()){
            throw new IllegalArgumentException ("uwu masta task list is empty " + AsciiArt.getArt("cry"));
        }
        int num = 0;
        for (Task task : processor.taskList) {
            num += 1;
            System.out.println(num + ". " + task.getStatus());
        }
    }
}
