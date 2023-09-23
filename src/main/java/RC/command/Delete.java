package RC.command;

import RC.RCException;
import RC.TaskList;
import RC.UI.Ui;

public class Delete extends RCCommand {
    private String index;
    
    public Delete(String index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws RCException {
        taskList.delete(index, ui);
    }
}
