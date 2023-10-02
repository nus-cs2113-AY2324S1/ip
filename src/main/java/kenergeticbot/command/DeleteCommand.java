package kenergeticbot.command;

import kenergeticbot.TaskList;
import kenergeticbot.ui.TextUi;

/**
 * Deletes a task identified using it's last displayed index from the task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the last task listing.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";
    private final int listIndex;
    public DeleteCommand(int listIndex) {
        this.listIndex = listIndex;
    }

    @Override
    public void execute(TaskList taskList, TextUi ui) {
        ui.printDeleteTaskMessage(taskList, listIndex);
        taskList.remove(listIndex - 1);
    }

}
