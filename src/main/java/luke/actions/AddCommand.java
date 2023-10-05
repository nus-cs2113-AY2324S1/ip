package luke.actions;

import luke.files.Storage;
import luke.tasks.*;
import luke.user.LukeTimeError;
import luke.user.Ui;

import static luke.actions.ActionType.*;

public class AddCommand extends Command {
    private Task latestTask;
    public AddCommand(ActionType theAction, String parameters) throws LukeTimeError {

        super(theAction, parameters);
        if (theAction == TODO) {
            latestTask = new Todo(parameters);
        }
        if (theAction == DEADLINE) {
            try {
                latestTask = new Deadline(parameters);
            } catch (LukeTimeError e) {
                System.out.println("\tOOPS!!! There's an error in the deadline's 'do by' date.");
            }
        }
        if (theAction == EVENT) {
            try {
                latestTask = new Event(parameters);
            } catch (LukeTimeError e) {
                System.out.println("\tOOPS!!! There's an error in the event's start and end time.");
            }
        }

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //ui has String echo, storage has ArrayList<Task> tasks, tasks has ArrayList<Task> mainTaskList;

        tasks.addTask(latestTask);

        System.out.println("\tGot it. I've added this task:" + "\n" + tasks.get(tasks.size() - 1));
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    }
}
