package dawson.commands;

import dawson.TaskList;

public abstract class Command {
    private static final String ADD_COMMAND = "add";
    private static final String LIST_COMMAND = "list";
    private static final String EXIT_COMMAND = "bye";
    
    public static Command getCommand(String input, TaskList taskList) {
        String[] split = input.split("\\s+", 2);
        String commandString = split[0]; // First word is command
        String item = split.length > 1 ? split[1] : ""; // Remaining input text

        switch (commandString) {
            case ADD_COMMAND: return new Add(item, taskList);
            case LIST_COMMAND: return new List(taskList);
            case EXIT_COMMAND: return new Exit();
            default: return new Echo(input);
        }
    };

    public abstract void execute();
}
