package elvis.command;

import elvis.operation.TaskList;
import elvis.operation.Ui;

public class UnmarkTaskCommand extends Command {
    public static void taskUnmarker(int numberInput) {
        int nthTask = numberInput - 1;
        int arraySize = TaskList.getArraySize();
        if (nthTask > arraySize-1 || nthTask < 0 || TaskList.isArrayEmpty()) {
            Ui.noSuchTaskMessagePrinter();
            return;
        }
        TaskList.setTaskStatus(nthTask, false);
        Ui.taskMarkedMessagePrinter();
        Ui.print(nthTask);
    }
}