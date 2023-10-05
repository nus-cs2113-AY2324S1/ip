package dawson.command;

import dawson.exception.DawsonException;
import dawson.task.Task;
import dawson.task.TaskList;

/**
 * Marks a task as done based on a given index
 */
public class MarkCommand extends Command {

    private String payload;

    public MarkCommand(String payload) {
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

        // Mark task as done
        Task markedTask = list.markAsDoneIndex(index);

        String[] markAsDoneTexts = {
            "Nice! I've marked this task as done: ",
            "  " + markedTask.toString()
        };
        return new CommandResult(markAsDoneTexts);
    }

}
