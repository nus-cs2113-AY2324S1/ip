package dawson.command;

import java.util.ArrayList;

import dawson.exception.DawsonException;
import dawson.task.TaskList;

public class FindCommand extends Command {

    private static final String MESSAGE_FIND_PROMPT = "Here are the matching tasks in your list";

    private String payload;

    public FindCommand(String payload) {
        this.payload = payload;
    }

    @Override
    public CommandResult execute(TaskList list) throws DawsonException {
        ArrayList<String> result = list.findTasks(payload);

        if (result.isEmpty()) {
            return new CommandResult("No task found with: " + payload);
        }

        result.add(0, MESSAGE_FIND_PROMPT);
        return new CommandResult(result.toArray(new String[result.size()]));
    }
    
}
