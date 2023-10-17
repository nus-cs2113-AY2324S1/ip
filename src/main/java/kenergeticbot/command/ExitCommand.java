package kenergeticbot.command;

import kenergeticbot.TaskList;
import kenergeticbot.ui.TextUi;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command{
    protected static boolean isExit = false;
    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(TaskList taskList, TextUi ui) {
        isExit = true;
    }

    public static boolean isExit() {
        return isExit;
    }
}
