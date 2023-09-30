package chattie.commands;

import chattie.TaskList;
import chattie.Ui;
import chattie.error.ChattieException;
import chattie.Storage;

public class EchoCommand extends Command {

    private static String command;

    public EchoCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws ChattieException {
        ui.printEchoMessage(command);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
