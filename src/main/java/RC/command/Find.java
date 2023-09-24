package RC.command;

import RC.RCException;
import RC.TaskList;
import RC.UI.Ui;
import RC.task.Task;

public class Find extends RCCommand {
    private String keyword;
    private static final String MESSAGE_FIND_TASKS = "\tHere are the matching tasks in your list:";
    private static final String MESSAGE_EMPTY = "\tOOPS!!! Please ensure the search keyword isn't empty.";

    public Find(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws RCException {
        if (keyword.isEmpty()) {
            throw new RCException(MESSAGE_EMPTY);
        }

        int count = 1;
        ui.showMessage(MESSAGE_FIND_TASKS);
        for (Task task : taskList.tasks) {
            String taskDesc = task.toString();
            if (taskDesc.contains(keyword)) {
                ui.showMessage("\t" + count + "." + taskDesc);
                count++;
            }
        }
    }
}
