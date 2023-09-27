package kenergeticbot.command;

import kenergeticbot.TaskList;

public class ExitCommand extends Command{
    protected static boolean isExit = false;
    public static final String COMMAND_WORD = "bye";

    public void execute(TaskList taskList) {
        isExit = true;
    }

    public static boolean isExit() {
        return isExit;
    }
}
