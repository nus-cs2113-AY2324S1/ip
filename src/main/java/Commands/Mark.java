package Commands;

import CSGPT.CSGPT;
import CSGPT.TaskList;

public class Mark extends Command {
    private final int taskNumber;
    private final TaskList list;

    public Mark(int taskNumber, TaskList list) {
        this.taskNumber = taskNumber;
        this.list = list;
    }

    @Override
    public void execute() {
        list.markAsDone(taskNumber);
        CSGPT.printText("Consider it done:\n" + list.getTask(taskNumber).toString());
    }
}
