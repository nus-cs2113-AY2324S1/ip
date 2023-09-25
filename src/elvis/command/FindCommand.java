package elvis.command;

import elvis.operation.TaskList;
import elvis.operation.Ui;
import elvis.task.Task;
import java.util.ArrayList;

public class FindCommand extends Command {
    public static void finder(String findCommand) {
        boolean hasMatch = false;
        String matchingWord = findCommand.substring(findCommand.indexOf("find") + 5).trim();
        ArrayList<Task> tasks = TaskList.getArray();
        ArrayList<Integer> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(matchingWord)) {
                hasMatch = true;
                matchingTasks.add(tasks.indexOf(task));
            }
        }
        Ui.findTaskMessagePrinter(hasMatch, matchingTasks);
    }
}
