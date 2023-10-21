package duke.parser;

import duke.command.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.tasklist.TaskList;

import java.util.HashMap;

/**
 * A <code>Parser</code> object is responsible for parsing and executing
 * a user provided command string.
 */
public class Parser {
    public Parser() {}

    /**
     * Returns a human-readable result of the command
     * executed.
     *
     * @param line inputted by user.
     * @param tasks that are currently in the TaskList
     * @return A human-readable string of the command's result.
     * @throws DukeException If command has issues.
     */
    public String executeCommand(String line, TaskList tasks) throws DukeException {
        int divider = line.indexOf(" ");
        if (divider == -1) {
            throw new DukeException("Sorry! Not sure what you mean");
        }
        if (divider == line.length() - 1) {
            throw new DukeException("Please enter a non-empty parameter value");
        }


        if (line.startsWith("todo")){
            String description = line.substring(divider + 1);

            return tasks.addTask(new Todo(description));
        } else if (line.startsWith("find")) {
            String keyword = line.substring(divider + 1);

            return tasks.getIndexedTasksByKeyword(keyword);
        }

        if (line.contains("mark") || line.startsWith("delete")) {
            int idx = Integer.parseInt(line.substring(divider + 1)) - 1;
            if (idx < 0 || idx >= tasks.size()) {
                throw new DukeException("Sorry! That's not a valid task");
            }

            if (line.contains("mark")) {
                return tasks.markTask(idx, line.startsWith("mark"));
            } else {
                return tasks.removeTask(idx);
            }
        }

        HashMap<String, String> parameters = parseParameters(line);
        String description = parameters.get("description");
        if (description == null) {
            throw new DukeException("Sorry! Please provide a valid description");
        }
        if (line.startsWith("deadline")) {
            String by = parameters.get("by");
            if (by == null) {
                throw new DukeException("Sorry! Please provide a valid `by`");
            }
            return tasks.addTask(new Deadline(description, by));
        } else if (line.startsWith("event")) {
            String from = parameters.get("from");
            String to = parameters.get("to");
            if (from == null || to == null) {
                throw new DukeException("Sorry! Please provide a valid `from` and/or `to`");
            }
            return tasks.addTask(new Event(description, from, to));
        } else {
            throw new DukeException("Sorry! Please enter a valid command");
        }
    }

    private static HashMap<String, String> parseParameters(String line) throws DukeException {
        HashMap<String, String> fieldToValue = new HashMap<>();

        int startDescription = line.indexOf(" ");
        int endOfDescription = line.indexOf(" /");
        if (startDescription == endOfDescription || startDescription == -1 || endOfDescription == -1) {
            throw new DukeException("Sorry! Not sure what you mean");
        }
        fieldToValue.put("description", line.substring(startDescription + 1, endOfDescription));

        String[] splitParams = line.split(" /");
        for (int i = 1; i < splitParams.length; i++) {
            String rawParam = splitParams[i];
            int divider = rawParam.indexOf(" ");
            if (divider == -1) {
                throw new DukeException("Sorry! Please enter valid inputs");
            }

            String field = rawParam.substring(0, divider);
            String value = rawParam.substring(divider + 1);
            fieldToValue.put(field, value);
        }

        return fieldToValue;
    }
}
