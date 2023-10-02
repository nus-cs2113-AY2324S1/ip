package kenergeticbot.command;

import kenergeticbot.TaskList;
import kenergeticbot.ui.TextUi;

/**
 * Represents an executable command.
 */
public class Command {

    /**
     * Executes the command and returns the result.
     */
    public void execute(TaskList taskList, TextUi ui) {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };
}
