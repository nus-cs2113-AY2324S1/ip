package Commands;

import CSGPT.CSGPT;
import CSGPT.Task;
import CSGPT.TaskList;
import Exceptions.CSGPTMissingTaskException;

import Ui.TextUi;

public class Delete extends Command {
    private final int taskNumber;
    public Delete(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList list) throws CSGPTMissingTaskException {
        try {
            list.delete(taskNumber);
            TextUi.printText("Noted. I've removed this task. Now you have " + list.size() + " tasks in the list.");
        } catch (CSGPTMissingTaskException e) {
            throw new CSGPTMissingTaskException();
        }
    }
}
