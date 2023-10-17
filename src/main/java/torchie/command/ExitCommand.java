package torchie.command;

import torchie.task.TaskList;
import torchie.ui.Ui;

public class ExitCommand extends Command {
    private Ui ui;
    public ExitCommand(TaskList tl) {
        this.ui = new Ui(tl);
    }

    @Override
    public void handleCommand() {
        ui.bye();
    }
}
