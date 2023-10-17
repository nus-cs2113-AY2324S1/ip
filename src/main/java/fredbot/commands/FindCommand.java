package fredbot.commands;

import fredbot.Storage;
import fredbot.TaskList;
import fredbot.Ui;
import fredbot.task.Task;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Represent a class for executing find task command
 */
public class FindCommand extends Command {
    private final String queryString;

    public FindCommand(String queryString) {
        this.queryString = queryString;
    }
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        int numTask = Task.getNumTask();
        Set<Integer> hash_Set = new HashSet<Integer>();
        for (int i = 0; i < numTask; i++) {
            Task task = tasks.getTask(i);
            if (task.getTaskDesc().toLowerCase().contains(queryString.toLowerCase())) {
                hash_Set.add(i);
            }
        }
        ui.printFoundTasks(tasks, hash_Set);
    }
}
