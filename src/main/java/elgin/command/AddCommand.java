package elgin.command;

import elgin.exception.DukeException;
import elgin.storage.Storage;
import elgin.task.TaskList;
import elgin.ui.Ui;

import static elgin.parser.Parser.isArguments;


public class AddCommand extends Command {
    private String command;
    private String arguments;

    /**
     * Constructor for AddCommand.
     *
     * @param command Command type.
     * @param arguments Arguments supplied by the user.
     */
    public AddCommand(String command, String arguments) {
        this.command = command.trim();
        this.arguments = arguments.trim();
    }

    /**
     * Executes to add Todo/Deadline/Event task into TaskList based on command.
     * Prints success message when task is added.
     *
     * @param tasks TaskList of tasks.
     * @param ui Ui for standardised output of program.
     * @param storage Storage that stores and loads tasks from disk.
     * @throws DukeException If error adding task.
     */
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
