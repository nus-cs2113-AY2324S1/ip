package bob.commands;

import bob.tasklist.TaskList;

/**
 * Signals application to exit.
 */
public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    @Override
    public String execute(TaskList taskList) {
        return "bye";
    }
}
