package kenergeticbot.command;

import kenergeticbot.TaskList;
import kenergeticbot.ui.TextUi;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public void execute(TaskList taskList) {
        TextUi.printLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.printf("     %d.%s\n", i+1, taskList.getTask(i));
        }
        TextUi.printLine();
    }
}
