package commands;

import dawson.TaskList;

public abstract class Command {
    private static final String ADD_COMMAND = "add";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String EXIT_COMMAND = "bye";

    public static Command getCommand(String input, TaskList taskList) {
        String[] split = input.split("\\s+", 2);
        String commandString = split[0]; // First word is command
        String payload = split.length > 1 ? split[1] : ""; // Remaining input text

        switch (commandString) {
            case ADD_COMMAND:
                return new Add(payload, taskList);
            case LIST_COMMAND:
                return new List(taskList);
            case MARK_COMMAND:
                return new Mark(payload, taskList);
            case UNMARK_COMMAND:
                return new Unmark(payload, taskList);
            case EXIT_COMMAND:
                return new Exit();
            default:
                return new Echo(input);
        }
    };

    public abstract void execute();
}
