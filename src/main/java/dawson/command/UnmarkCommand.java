package dawson.command;

import dawson.exception.DawsonException;
import dawson.task.Task;
import dawson.task.TaskList;

/**
 * Marks a task as NOT done based on a given index
 */
public class UnmarkCommand extends Command {

    private String payload;

    public UnmarkCommand(String payload) {
        this.payload = payload;
    }

    @Override
    public CommandResult execute(TaskList list) throws DawsonException {
        // Convert index into integer, ensure it is valid integer
        int index;
        try {
            index = Integer.parseInt(payload);
            index--; // Convert to 0-base indexing
        } catch (NumberFormatException e) {
            String errorMsg = "Invalid index! Unable to parse into integer";
            throw new DawsonException(errorMsg);
        }

        // Mark task as NOT done
        Task unmarkedTask = list.unmarkIndex(index);
        
        String[] unmarkIndexTexts = {
            "Ok, I've marked this task as not done yet: ",
            "  " + unmarkedTask.toString()
        };
        return new CommandResult(unmarkIndexTexts);
    }

}
