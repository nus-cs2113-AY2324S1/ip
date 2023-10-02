package dawson.command;

import dawson.exception.DawsonException;
import dawson.task.Task;
import dawson.task.TaskList;

public class UnmarkCommand extends Command {

    private String payload;
    private TaskList taskList;

    public UnmarkCommand(String payload, TaskList taskList) {
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

        // Mark task as NOT done
        Task unmarkedTask = taskList.unmarkIndex(index);
        
        String[] unmarkIndexTexts = {
            "Ok, I've marked this task as not done yet: ",
            "  " + unmarkedTask.toString()
        };
        return new CommandResult(unmarkIndexTexts);
    }

}
