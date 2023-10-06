package commands;

import ascii.AsciiArt;
import main.Parser;
/**
 * Represents a delete Command to remove a task in the task list
 */
public class DeleteCommand extends Command {
    @Override
    public void execute(String statement, Parser processor) throws IllegalArgumentException {
        int number = parseInt(statement, processor);
        System.out.println("Okay master! I have removed this task: ");
        System.out.println(processor.taskList.get(number).getStatus());
        processor.taskList.remove(number);
        System.out.println("Now you have " + processor.taskList.size() + " tasks in the list masta " + AsciiArt.getArt("uwu"));
    }
}
