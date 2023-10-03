package chattie.commands;

import chattie.TaskList;
import chattie.Ui;
import chattie.error.ChattieException;
import chattie.Storage;

/**
 * Deals with echo related commands
 */
public class EchoCommand extends Command {

    private static String command;

    public EchoCommand(String command) {
        this.command = command;
    }

    /**
     * Echos the user input
     *
     * @param tasks List of tasks
     * @param ui User interface
     * @throws ChattieException
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws ChattieException {
        ui.printEchoMessage(command);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
