package chat0pt.commands;

import chat0pt.helper.DukeException;
import chat0pt.parser.Parser;
import chat0pt.storage.Storage;
import chat0pt.tasks.TaskList;
import chat0pt.ui.Ui;

public class DeleteCommand extends Command {
    private String[] commandString;

    public DeleteCommand(String[] commandString) {
        this.commandString = commandString;
    }

    @Override
    public void runCommand(Ui ui, Storage storage, TaskList tasks) throws DukeException {
        int taskNumber = Parser.validNumberInput(commandString);
        if (taskNumber >= 0) {
            tasks.deleteTask(taskNumber);
        } else {
            ui.invalidDelete();
        }
    }
}
