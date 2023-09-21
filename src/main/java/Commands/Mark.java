package Commands;

import CSGPT.CSGPT;
import CSGPT.TaskList;
import Exceptions.CSGPTMissingTaskException;

import Ui.TextUi;

public class Mark extends Command {
    private final int taskNumber;
    private final boolean isDone;

    public Mark(int taskNumber, boolean isDone) {
        this.taskNumber = taskNumber;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList list) throws CSGPTMissingTaskException {
        try {
            list.mark(taskNumber, isDone);
            TextUi.printText("Consider it done:\n\t" + list.getTask(taskNumber).toString());
        } catch (CSGPTMissingTaskException e) {
            throw new CSGPTMissingTaskException();
        }
    }
}
