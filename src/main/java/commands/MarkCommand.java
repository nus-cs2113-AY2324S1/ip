package commands;

import ascii.AsciiArt;
import main.ResponseProcessor;
/**
 * Represents a mark Command to mark a task as complete
 */
public class MarkCommand extends Command {
    @Override
    public void execute(String statement, ResponseProcessor processor) throws IllegalArgumentException {
        int number = parseInt(statement, processor);
        processor.taskList.get(number).setCompleted(true);
        System.out.println("Nicu! I have marked this as done master! " + AsciiArt.getArt("good"));
        System.out.println(processor.taskList.get(number).getStatus());
    }
}
