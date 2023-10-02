package dawson.command;

import dawson.exception.DawsonException;
import dawson.task.Task;
import dawson.task.TaskList;

public class MarkCommand extends Command {

    private String payload;
    private TaskList taskList;

    public MarkCommand(String payload, TaskList taskList) {
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

        // Mark task as done
        Task markedTask = taskList.markAsDoneIndex(index);

        String[] markAsDoneTexts = {
            "Nice! I've marked this task as done: ",
            "  " + markedTask.toString()
        };
        return new CommandResult(markAsDoneTexts);
    }

}
