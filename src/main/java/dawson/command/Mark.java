package dawson.command;

import dawson.TaskList;
import dawson.Dawson;
import dawson.DawsonException;

public class Mark extends Command {

    private String payload;
    private TaskList taskList;

    public Mark(String payload, TaskList taskList) {
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

        // Execute the mark as done
        String updatedTask = taskList.markAsDoneIndex(index);
        String doneText = "Nice! I've marked this task as done: " + System.lineSeparator();
        doneText += "\t " + updatedTask;
        Dawson.printText(doneText);
    }

}
