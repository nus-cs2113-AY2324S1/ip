package Commands;

import Data.TaskList;
import Exceptions.CSGPTMissingTaskException;

import Ui.Ui;

public class Mark extends Command {
    private final int taskNumber;
    private final boolean isDone;

    public Mark(int taskNumber, boolean isDone) {
        this.taskNumber = taskNumber;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList list, Ui ui) throws CSGPTMissingTaskException {
        try {
            list.mark(taskNumber, isDone);
            ui.printText("Consider it done:\n\t" + list.getTask(taskNumber).toString());
        } catch (CSGPTMissingTaskException e) {
            throw new CSGPTMissingTaskException();
        }
    }
}
