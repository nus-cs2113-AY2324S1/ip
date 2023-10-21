package linguobot.command;

import linguobot.Ui;
import linguobot.task.Task;
import linguobot.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class FindTask extends Command{
    private final String keyword;
    private final TaskList taskList;

    public FindTask(String keyword, TaskList taskList) {
        this.keyword = keyword;
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        boolean found = false;
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList.getTaskList()) {
            if (task.getDescription().contains(keyword)) {
                found = true;
                matchingTasks.add(task);
            }
        }
        if (found) {
            Ui.printLine();
            Ui.printTextWithoutLine("Here are the matching tasks in your list:");
            for (Task task : matchingTasks) {
                Ui.printTextWithoutLine(task.toString());
            }
            Ui.printLine();
        }
        else {
            Ui.printText("No such task found.");
        }
    }
}
