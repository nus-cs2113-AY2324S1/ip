package dawson.command;

import dawson.DawsonException;
import dawson.TaskList;

public class Delete extends Command {

    private String payload;
    private TaskList taskList;

    public Delete(String payload, TaskList taskList) {
        this.payload = payload;
        this.taskList = taskList;
    }

    @Override
    public void execute() throws DawsonException {
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
        taskList.deleteTask(index);
    }
}