package luke.actions;

import luke.files.Storage;
import luke.tasks.TaskList;
import luke.user.Ui;

/**
 * The FindCommand Class represents a command for finding tasks in the Luke application based on a keyword.
 * It extends the Command class and includes specific behavior for finding tasks.
 */
public class FindCommand extends Command {

    /**
     * Constructs a FindCommand with the specified action type and keyword parameters.
     *
     * @param theAction   The action type (FIND).
     * @param parameters  The keyword parameters used for searching tasks.
     */
    public FindCommand(ActionType theAction, String parameters) {
        super(theAction, parameters);
    }

    /**
     * Executes the FindCommand to search for and display tasks that match the provided keyword.
     *
     * @param tasks    The task list to search within.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving task changes (not used in this case).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("\tHere are the matching tasks in your list:");
        int j = 1;
        for (int i = 0; i < tasks.size(); i += 1) {
            if (tasks.get(i).getDescription().contains(parameters)) {
                System.out.println("\t" + j + "." + tasks.get(i));
                j += 1;
            }
        }
    }
}
