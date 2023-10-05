package dawson.command;

import java.util.ArrayList;

import dawson.exception.DawsonException;
import dawson.task.TaskList;

/**
 * Finds all tasks with desciption matching a query string.
 */
public class FindCommand extends Command {

    private static final String MESSAGE_EMPTY_QUERY = "Empty query found! Please enter non-empty search query";
    private static final String MESSAGE_CANNOT_FIND = "No task found with: %s";
    private static final String MESSAGE_FIND_PROMPT = "Here are the tasks matching: %s";

    private String payload;

    public FindCommand(String payload) {
        this.payload = payload;
    }

    @Override
    public CommandResult execute(TaskList list) throws DawsonException {
        ArrayList<String> result = list.findTasks(payload);

        if (isQueryEmpty(payload)) {
            return new CommandResult(MESSAGE_EMPTY_QUERY);
        } else if (result.isEmpty()) {
            return new CommandResult(String.format(MESSAGE_CANNOT_FIND, payload));
        }

        result.add(0, String.format(MESSAGE_FIND_PROMPT, payload));
        return new CommandResult(result.toArray(new String[0]));
    }

    private boolean isQueryEmpty(String query) {
        return query.trim().length() == 0;
    }
    
}
