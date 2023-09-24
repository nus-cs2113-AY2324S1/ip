package elvis.command;

import elvis.operation.TaskList;
import elvis.operation.Ui;

public class MarkTaskCommand extends Command {
    public static void taskMarker(int numberInput) {
        int nthTask = numberInput - 1;
        int arraySize = TaskList.getArraySize();
        if (nthTask > arraySize-1 || nthTask < 0 || TaskList.isArrayEmpty()) {
            Ui.noSuchTaskMessagePrinter();
            return;
        }
        TaskList.setTaskStatus(nthTask, true);
        Ui.taskMarkedMessagePrinter();
        Ui.print(nthTask);
    }
}
