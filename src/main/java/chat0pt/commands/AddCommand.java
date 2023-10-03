package chat0pt.commands;

import chat0pt.helper.DukeException;
import chat0pt.storage.Storage;
import chat0pt.tasks.TaskList;
import chat0pt.ui.Ui;

public class AddCommand extends Command {
    private String type;
    private String[] tokens;

    /**
     * Constructor for the AddCommand function
     *
     * @param type   Type of command (Todo, Event, Deadline)
     * @param tokens User input after splitting by space
     */
    public AddCommand(String type, String[] tokens) {
        this.type = type;
        this.tokens = tokens;
    }

    /**
     * Determines which action to take based on the command type
     * @param ui  Takes in Ui for printing
     * @param tasks Current TaskList
     */
    @Override
    public void runCommand(Ui ui, TaskList tasks) {
        switch (type) {
        case "todo":
            tasks.addTodo(tokens);
            break;
        case "deadline":
            tasks.addDeadline(tokens);
            break;
        case "event":
            tasks.addEvent(tokens);
            break;
        default:
            ui.unsupportedCommand();
        }

    }
}
