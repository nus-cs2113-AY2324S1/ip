package elvis.command;

import elvis.operation.TaskList;
import elvis.operation.Ui;

/**
 * Command class for marking task in TaskList
 */
public class MarkTaskCommand extends Command {
    /**
     * Marks task in the TaskList
     *
     * @param numberInput
     */
    public static void taskMarker(int numberInput) {
        int nthTask = numberInput - 1;
        int arraySize = TaskList.getArraySize();
        if (nthTask >= arraySize || nthTask < 0 || TaskList.isArrayEmpty()) {
            Ui.noSuchTaskMessagePrinter();
            return;
        }
        TaskList.setTaskStatus(nthTask, true);
        Ui.taskMarkedMessagePrinter();
        Ui.print(nthTask);
    }
}
