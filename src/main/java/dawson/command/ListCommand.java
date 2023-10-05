package dawson.command;

import java.util.ArrayList;

import dawson.task.TaskList;

/**
 * Lists all tasks in the TaskList to the user.
 */
public class ListCommand extends Command {

    private static final String MESSAGE_EMPTY_LIST = "Empty list!";
    private static final String MESSAGE_LIST_PROMPT = "Here are the tasks in your list: ";
    
    @Override
    public CommandResult execute(TaskList list) {
        ArrayList<String> taskArray = list.getTaskList();
        if (taskArray.isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_LIST);
        }

        taskArray.add(0, MESSAGE_LIST_PROMPT);
        return new CommandResult(taskArray.toArray(new String[0]));
    }
    
}
