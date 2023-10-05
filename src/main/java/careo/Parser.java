package careo;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    private Ui ui;
    private TaskList tasks;

    public Parser(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Parses and processes a line of text inputted by the user.
     *
     * @param input The input from the user.
     * @return Whether the process-input-loop should be exited.
     */
    public boolean parseInput(String input) {
        ArrayList<String> keywords = new ArrayList<>(
                Arrays.asList("mark", "unmark", "deadline", "event", "todo")
        );

        if (input.equals("bye")) {
            return true;
        }

        if (keywords.contains(input)) {
            ui.showCommandArgumentMissingError(input);
        } else if (input.equals("list")) {
            ui.listTasks(tasks);
        } else if (input.startsWith("mark ")) {
            int taskIdx = Integer.parseInt(input.substring(5)) - 1;

            tasks.markOrUnmarkTask(MarkOrUnmark.MARK, taskIdx);
        } else if (input.startsWith("unmark ")) {
            int taskIdx = Integer.parseInt(input.substring(7)) - 1;

            tasks.markOrUnmarkTask(MarkOrUnmark.UNMARK, taskIdx);
        } else if (input.startsWith("deadline ")) {
            String relevantInput = input.substring(9);

            String[] parts = relevantInput.split("/");
            String taskDescription = parts[0].strip();
            String by = parts[1].substring(2).strip();

            tasks.addTask(new Deadline(taskDescription, by));
        } else if (input.startsWith("event ")) {
            String relevantInput = input.substring(6);

            String[] parts = relevantInput.split("/");
            String taskDescription = parts[0].strip();
            String from = parts[1].substring(4).strip();
            String to = parts[2].substring(2).strip();

            tasks.addTask(new Event(taskDescription, from, to));
        } else if (input.startsWith("todo ")) {
            String relevantInput = input.substring(5);

            String taskDescription = relevantInput.strip();

            tasks.addTask(new ToDo(taskDescription));
        } else if (input.startsWith("delete ")) {
            int taskIdx = Integer.parseInt(input.substring(7)) - 1;

            tasks.removeTask(taskIdx);
        } else if (input.startsWith("find ")) {
            String searchTerm = input.substring(5);

            ArrayList<Task> matchingTasks = tasks.findMatchingTasks(searchTerm);

            ui.showMatchingTasks(matchingTasks);
        } else {
            ui.showUnrecognizedCommandError();
        }

        return false;
    }
}
