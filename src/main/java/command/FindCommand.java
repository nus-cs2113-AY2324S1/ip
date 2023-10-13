package command;

import task.Task;
import ui.Ui;
import task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructor
     *
     * @param keyword the keyword to find in task
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by calling findTask method in Tasklist
     *
     * @param taskList the taskList of the current user
     * @param ui the ui for the current session
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ArrayList<Task> matchingTasks = taskList.findTask(this.keyword);
        Ui.printMatchingTasks(matchingTasks);
    }
}
