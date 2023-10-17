package commands;

import ascii.AsciiArt;
import main.Parser;
/**
 * Represents an unmark Command to unmark a task as complete
 */
public class UnmarkCommand extends Command {
    @Override
    public void execute(String statement, Parser processor) throws IllegalArgumentException {
        int number = parseInt(statement, processor);
        processor.taskList.get(number).setCompleted(false);
        System.out.println("Okay master! I have marked this task as not done! " + AsciiArt.getArt("okay"));
        System.out.println(processor.taskList.get(number).getStatus());
    }
}
