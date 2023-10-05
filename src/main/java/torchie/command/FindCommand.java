package torchie.command;

import torchie.task.TaskList;
import torchie.task.Task;

public class FindCommand extends Command{

    private TaskList taskList;
    private String keyword;

    public FindCommand(TaskList tl, String kw) {
        this.taskList = tl;
        this.keyword = kw;
    }

    @Override
    public void handleCommand() {
        // go through every item in the list
        TaskList matchingTasks = taskList.findTask(keyword);
        System.out.println("Here are the matching tasks in your list: ");
        matchingTasks.showTasks();
    }
}
