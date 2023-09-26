package commands;

import ascii.AsciiArt;
import main.ResponseProcessor;
import task.Deadline;
/**
 * Represents a deadline Command to add a new deadline task to the task list
 */
public class DeadlineCommand extends Command {

    @Override
    public void execute(String statement, ResponseProcessor processor) throws IllegalArgumentException {
        int indexBy = getIndex(statement,"/by ");
        String description = statement.substring(0, indexBy);
        String by = processDate(statement.substring(indexBy + 4));
        isValidTask(description);
        if (!by.isBlank()) {
            processor.taskList.add(new Deadline(description, by));
            System.out.println("added: " + processor.taskList.get(processor.taskList.size() - 1).getStatus());
            System.out.println("Now you have " + processor.taskList.size() + " tasks in the list masta " + AsciiArt.getArt("uwu"));
        } else {
            throw new IllegalArgumentException ("Your /by is empty uwu! " + AsciiArt.getArt("sad"));
        }
    }

}
