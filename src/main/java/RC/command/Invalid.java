package RC.command;

import RC.RCException;
import RC.TaskList;
public class Invalid extends RCCommand {
    public Invalid() {

    }

    @Override
    public void execute(TaskList taskList) throws RCException {
        String message = "\tOOPS!!! I'm sorry, but I don't know what that means :-(";
        System.out.println(message);
    }
}
