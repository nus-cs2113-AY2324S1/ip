package Commands;

import CSGPT.CSGPT;
import CSGPT.TaskList;
import Exceptions.CSGPTMissingTaskException;

public class Mark extends Command {
    private final int taskNumber;
    private final TaskList list;

    public Mark(int taskNumber, TaskList list) {
        this.taskNumber = taskNumber;
        this.list = list;
    }

    @Override
    public void execute() throws CSGPTMissingTaskException {
        try {
            list.markAsDone(taskNumber);
            CSGPT.printText("Consider it done:\n\t" + list.getTask(taskNumber).toString());
        } catch (CSGPTMissingTaskException e) {
            throw new CSGPTMissingTaskException();
        }
    }
}
