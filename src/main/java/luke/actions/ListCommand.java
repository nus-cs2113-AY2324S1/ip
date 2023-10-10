package luke.actions;

import luke.files.Storage;
import luke.tasks.TaskList;
import luke.user.Ui;

/**
 * The ListCommand Class represents a command for listing tasks in the Luke application.
 * It extends the Command class and includes specific behavior for listing tasks.
 */
public class ListCommand extends Command {
    public ListCommand(ActionType theAction, String parameters) {
        super(theAction, parameters);
    }

    /**
     * Executes the ListCommand to display the list of tasks.
     *
     * @param tasks    The task list to be displayed.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving task changes (not used in this case).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i += 1) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i));
        }
    }
}
