package kenergeticbot.command;

import kenergeticbot.TaskList;
import kenergeticbot.ui.TextUi;

/**
 * Lists all tasks in the task list to the user.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    @Override
    public void execute(TaskList taskList, TextUi ui) {
        ui.printLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.printf("     %d.%s\n", i+1, taskList.getTask(i));
        }
        ui.printLine();
    }
}
