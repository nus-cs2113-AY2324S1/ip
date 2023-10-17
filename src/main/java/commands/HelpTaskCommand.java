package commands;

import taskmanagement.Task;
import taskmanagement.Storage;
import taskmanagement.TaskList;
import userinputs.Ui;

import java.util.ArrayList;

/**
 * Command to display help information in the Zran application
 * Extends the abstract Commands class.
 */
public class HelpTaskCommand extends Commands {

    /**
     * Constructs a HelpTaskCommand instance with the given input.
     *
     * @param input User's input into the application.
     */
    public HelpTaskCommand(String input) {
        super(input);
    }

    /**
     *  Executes the help task command by showing help information.
     *
     * @param tasks   The task list of class 'TaskList'. (Not used in this function)
     * @param ui      The ui component of class 'UI' for displaying messages.
     * @param storage The storage component of class 'Storage' for saving task data. (Not used in this function)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showHelp();
    }

}
