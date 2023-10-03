package chat0pt.commands;

import chat0pt.helper.DukeException;
import chat0pt.storage.Storage;
import chat0pt.tasks.TaskList;
import chat0pt.ui.Ui;

public class AddCommand extends Command {
    private String type;
    private String[] message;

    public AddCommand(String type, String[] message) {
        this.type = type;
        this.message = message;
    }

    @Override
    public void runCommand(Ui ui, Storage storage, TaskList tasks) throws DukeException {
        switch (type) {
        case "todo":
            tasks.addTodo(message);
            break;
        case "deadline":
            tasks.addDeadline(message);
            break;
        case "event":
            tasks.addEvent(message);
            break;
        default:
            ui.unsupportedCommand();
        }

    }
}
