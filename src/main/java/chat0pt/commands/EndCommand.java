package chat0pt.commands;

import chat0pt.helper.DukeException;
import chat0pt.storage.Storage;
import chat0pt.tasks.TaskList;
import chat0pt.ui.Ui;

public class EndCommand extends Command {
    @Override
    public void runCommand(Ui ui, Storage storage, TaskList tasks) throws DukeException {
        endProgram = true;
    }
}
