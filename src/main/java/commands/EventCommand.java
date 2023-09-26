package commands;

import ascii.AsciiArt;
import main.ResponseProcessor;
import task.Event;
/**
 * Represents a deadline Command to add a new event task to the task list
 */
public class EventCommand extends Command {
    @Override
    public void execute(String statement, ResponseProcessor processor) throws IllegalArgumentException {
        int indexFrom = getIndex(statement, "/from");
        int indexTo = getIndex(statement, "/to");
        String description = statement.substring(0, indexFrom);
        String from = statement.substring(indexFrom + 5, indexTo);
        String to = statement.substring(indexTo + 3);
        isValidTask(description);
        if (!from.isEmpty() && !to.isEmpty()) {
            processor.taskList.add(new Event(description, from, to));
            System.out.println("added: " + processor.taskList.get(processor.taskList.size() - 1).getStatus());
            System.out.println("Now you have " + processor.taskList.size() + " tasks in the list masta " + AsciiArt.getArt("uwu"));
        } else {
            throw new IllegalArgumentException ("Your /from or /to is empty uwu! " + AsciiArt.getArt("sad"));
        }
    }
}
