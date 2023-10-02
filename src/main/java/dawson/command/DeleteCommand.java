package dawson.command;

import dawson.exception.DawsonException;
import dawson.task.Task;
import dawson.task.TaskList;

public class DeleteCommand extends Command {

    private String payload;
    private TaskList taskList;

    public DeleteCommand(String payload, TaskList taskList) {
        this.payload = payload;
        this.taskList = taskList;
    }

    @Override
    public CommandResult execute() throws DawsonException {
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
        Task removedTask = taskList.deleteTask(index);

        String[] deleteText = {
                "Noted. I've removed this task:",
                "  " + removedTask.toString(),
                String.format("Now you have %d tasks in the list.", taskList.getSize())
        };
        return new CommandResult(deleteText);
    }
}