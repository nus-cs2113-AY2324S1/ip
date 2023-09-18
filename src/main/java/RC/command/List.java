package RC.command;

import RC.RCException;
import RC.TaskList;
import RC.task.Task;

public class List extends RCCommand {
    public List() {
    }

    @Override
    public void execute(TaskList taskList) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < taskList.tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + taskList.tasks.get(i));
        }
    }
}
