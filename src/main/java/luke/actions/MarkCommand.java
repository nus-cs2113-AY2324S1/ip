package luke.actions;

import luke.files.Storage;
import luke.tasks.TaskList;
import luke.user.Ui;

import static luke.actions.ActionType.MARK;
import static luke.actions.ActionType.UNMARK;


public class MarkCommand extends Command {
    private boolean isDone;
    public MarkCommand(ActionType theAction, String parameters) {

        super(theAction, parameters);
        if (theAction == MARK) {
            isDone = true;
        }
        if (theAction == UNMARK) {
            isDone = false;
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //ui has String echo, storage has ArrayList<Task> tasks, tasks has ArrayList<Task> mainTaskList;
        //command has theActionWord and parameters

        int taskNumber = Integer.parseInt(parameters) - 1;

        try {
            tasks.get(taskNumber).setDone(isDone);
            System.out.println(tasks.get(taskNumber));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tOOPS!!! Your arguments for " + theAction + " exceeds your task list.");
        }

    }
}
