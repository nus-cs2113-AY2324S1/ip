package chat0pt.commands;

import chat0pt.helper.DukeException;
import chat0pt.parser.Parser;
import chat0pt.storage.Storage;
import chat0pt.tasks.TaskList;
import chat0pt.ui.Ui;

public class MarkerCommand extends Command {
    private String[] commandString;
    private boolean marker; // True indicates to mark, False indicates to unmark

    public MarkerCommand(String[] commandString, boolean marker) {
        this.commandString = commandString;
        this.marker = marker;
    }

    /**
     * Used for marking/unmarking a task
     * @param ui Used for printing
     * @param tasks Current tasklist
     * @throws DukeException When a bot-specific error is encountered.
     */
    @Override
    public void runCommand(Ui ui, TaskList tasks) throws DukeException {
        int taskNumber = Parser.validNumberInput(commandString);
        if (taskNumber >= 0) {
            tasks.marker(taskNumber, marker);
        } else {
            ui.invalidMark();
        }
    }
}
