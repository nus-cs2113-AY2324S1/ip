package dawson.command;

import dawson.TaskList;
import dawson.Dawson;

public class Mark extends Command {

    private String payload;
    private TaskList taskList;

    public Mark(String payload, TaskList taskList) {
        this.payload = payload;
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        // Convert index into integer, ensure it is valid integer
        int index;
        try {
            index = Integer.parseInt(payload);
        } catch (NumberFormatException e) {
            Dawson.printText("Invalid index! Unable to parse into integer");
            return;
        }

        // Check if index exists in tasklist
        index--; // Convert to 0-base indexing
        if (!taskList.isIndexValid(index)) {
            Dawson.printText(payload + " index out of range of task list!");
            return;
        }

        // Execute the mark as done
        String updatedTask = taskList.markAsDoneIndex(index);
        String doneText = "Nice! I've marked this task as done: " + System.lineSeparator();
        doneText += "\t " + updatedTask;
        Dawson.printText(doneText);
    }

}
