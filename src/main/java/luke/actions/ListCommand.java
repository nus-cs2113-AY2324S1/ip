package luke.actions;

import luke.files.Storage;
import luke.tasks.TaskList;
import luke.user.Ui;

public class ListCommand extends Command {
    public ListCommand(ActionType theAction, String parameters) {
        super(theAction, parameters);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //ui has String echo, storage has ArrayList<Task> tasks, tasks has ArrayList<Task> mainTaskList;


        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i += 1) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i));
        }

    }
}
