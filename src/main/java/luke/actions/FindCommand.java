package luke.actions;

import luke.files.Storage;
import luke.tasks.TaskList;
import luke.user.Ui;

public class FindCommand extends Command {
    public FindCommand(ActionType theAction, String parameters) {
        super(theAction, parameters);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //ui has String echo, storage has ArrayList<Task> tasks, tasks has ArrayList<Task> mainTaskList;
        //command has theActionWord and parameters

        //String findWord = ui.echo.substring(4);
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
