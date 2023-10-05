package luke.actions;

import luke.files.Storage;
import luke.tasks.*;
import luke.user.LukeTimeError;
import luke.user.Ui;

import static luke.actions.ActionType.*;

/**
 * The AddCommand Class represents a command for adding a task to the Luke application.
 * It extends the Command class and includes specific behavior for adding different types of tasks.
 */
public class AddCommand extends Command {
    /**
     * The latest task created as a result of the add command.
     */
    private Task latestTask;

    /**
     * Constructs an AddCommand with the specified action type and parameters.
     *
     * @param theAction   The action type (TODO, DEADLINE, EVENT).
     * @param parameters  The parameters provided with the command (task description or task details).
     * @throws LukeTimeError If there are missing or invalid arguments for creating the task.
     */
    public AddCommand(ActionType theAction, String parameters) throws LukeTimeError {

        super(theAction, parameters);
        if (theAction == TODO) {
            latestTask = new Todo(parameters);
        }
        if (theAction == DEADLINE) {
            try {
                latestTask = new Deadline(parameters);
            } catch (LukeTimeError e) {
                System.out.println("\tOOPS!!! You have missing/invalid arguments for deadline. No changes have been made.");
                throw new LukeTimeError();
            }
        }
        if (theAction == EVENT) {
            try {
                latestTask = new Event(parameters);
            } catch (LukeTimeError e) {
                System.out.println("\tOOPS!!! You have missing/invalid arguments for event. No changes have been made.");
                throw new LukeTimeError();
            }
        }

    }

    /**
     * Executes the AddCommand to add the latest task to the task list.
     *
     * @param tasks    The task list to which the task will be added.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving task changes (not used in this case).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //ui has String echo, storage has ArrayList<Task> tasks, tasks has ArrayList<Task> mainTaskList;

        tasks.addTask(latestTask);

        System.out.println("\tGot it. I've added this task:" + "\n" + tasks.get(tasks.size() - 1));
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    }
}
