package commands;

import taskmanagement.Task;
import taskmanagement.Storage;
import taskmanagement.TaskList;
import userinputs.Ui;

import java.util.ArrayList;
/**
 * Lists all tasks from the task list in the Zran application.
 * Extends the abstract Commands class.
 */
public class ListTaskCommand extends Commands {
    /**
     * Constructs a ListTaskCommand instance with the given input.
     *
     * @param input User's input into the application.
     */
    public ListTaskCommand(String input) {
        super(input);
    }

    /**
     * Executes the list task command by displaying the list of tasks.
     *
     * @param tasks   The task list of class 'TaskList' to be displayed.
     * @param ui      The ui component of class 'UI' for displaying messages.
     * @param storage The storage component of class 'Storage' for saving task data. (Not used in this function)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.echo(tasks.listItems);
    }

}
