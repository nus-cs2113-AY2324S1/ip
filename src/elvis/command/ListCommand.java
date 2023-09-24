package elvis.command;

import elvis.operation.TaskList;
import elvis.operation.Ui;

public class ListCommand extends Command {
    public static void listOut(boolean isFromFile) {
        if (!isFromFile) {
            Ui.listMessagePrinter();
        }
        int arraySize = TaskList.getArraySize();
        for (int i = 0; i < arraySize; i++) {
            System.out.print(i + 1 + ".");
            Ui.print(i);
        }
        Ui.taskCountPrinter();
    }
}
