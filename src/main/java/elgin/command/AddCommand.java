package elgin.command;

import elgin.exception.DukeException;
import elgin.storage.Storage;
import elgin.task.TaskList;
import elgin.ui.Ui;

import static elgin.parser.Parser.isArguments;

public class AddCommand extends Command {
    private String command;
    private String arguments;

    public AddCommand(String command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] msg;
        if (!isArguments(arguments)) {
            return;
        }

        switch (command) {
        case "todo":
            msg = tasks.addTodo(arguments);
            break;
        case "deadline":
            msg = tasks.addDeadline(command, arguments);
            break;
        case "event":
            msg = tasks.addEvent(command, arguments);
            break;
        default:
            ui.showUnknownCmdErrorMsg();
            return;
        }

        ui.formatPrint(msg);
    }
}
