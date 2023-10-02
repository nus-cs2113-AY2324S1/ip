package dawson.command;

import dawson.exception.DawsonException;
import dawson.task.Task;
import dawson.task.TaskList;

public class DeleteCommand extends Command {

    private String payload;

    public DeleteCommand(String payload) {
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

        // Execute the delete command on index
        Task removedTask = list.deleteTask(index);

        String[] deleteText = {
                "Noted. I've removed this task:",
                "  " + removedTask.toString(),
                String.format("Now you have %d tasks in the list.", list.getSize())
        };
        return new CommandResult(deleteText);
    }
}