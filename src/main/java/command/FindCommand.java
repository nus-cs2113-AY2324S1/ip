package command;

import task.Task;
import ui.Ui;
import task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;
    public FindCommand(String keyWord) {
        this.keyword = keyWord;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ArrayList<Task> matchingTasks = taskList.findTask(this.keyword);
        Ui.printMatchingTasks(matchingTasks);
    }
}
