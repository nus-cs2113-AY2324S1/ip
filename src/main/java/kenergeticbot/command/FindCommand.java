package kenergeticbot.command;

import kenergeticbot.TaskList;
import kenergeticbot.ui.TextUi;

import static kenergeticbot.common.Messages.FIND_TASK_MESSAGE;
import static kenergeticbot.common.Messages.SEPARATING_LINE;

/**
 * Finds a task identified using a keyword.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private final String keyword;
    public FindCommand(String keyword) {
        this.keyword = "(.*)" + keyword + "(.*)";
    }

    @Override
    public void execute(TaskList taskList, TextUi ui) {
        ui.showToUser(SEPARATING_LINE, FIND_TASK_MESSAGE);
        int j = 1;
        for (int i = 0; i < taskList.getSize(); i++) {
            String task = String.valueOf(taskList.getTask(i));
            if (task.matches(keyword)) {
                ui.showToUser("     " + j + "." + taskList.getTask(i));
                j++;
            }
        }
        ui.showToUser(SEPARATING_LINE);
    }
}
