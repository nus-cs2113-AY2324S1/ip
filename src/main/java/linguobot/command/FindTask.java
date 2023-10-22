package linguobot.command;

import linguobot.Ui;
import linguobot.task.Task;
import linguobot.task.TaskList;

import java.util.ArrayList;

/**
 * The FindTask class represents a command to find tasks in the taskList that match a specified keyword.
 */
public class FindTask extends Command{
    private final String keyword;
    private final TaskList taskList;

    /**
     * Constructs a FindTask command with the given keyword and taskList.
     * @param keyword The keyword to search for in task descriptions.
     * @param taskList The taskList to search for matching tasks.
     */
    public FindTask(String keyword, TaskList taskList) {
        this.keyword = keyword;
        this.taskList = taskList;
    }

    /**
     * Executes the FindTask command by searching for tasks in the taskList whose descriptions contain
     * the specified keyword. It then prints the matching tasks or a message indicating that no matches were found.
     */
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
