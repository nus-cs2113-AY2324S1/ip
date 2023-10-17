package kenergeticbot.command;

import kenergeticbot.TaskList;
import kenergeticbot.ui.TextUi;

/**
 * Unmarks a task identified using it's last displayed index from the task list.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number used in the last task listing as not done.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";
    private final int listIndex;
    public  UnmarkCommand(int listIndex) {
        this.listIndex = listIndex;
    }
    @Override
    public void execute(TaskList taskList, TextUi ui) {
        taskList.getTask(listIndex - 1).unmark();
        ui.printUnmarkTaskMessage(taskList, listIndex);
    }
}
