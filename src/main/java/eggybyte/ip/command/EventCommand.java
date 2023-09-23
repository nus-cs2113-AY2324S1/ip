package eggybyte.ip.command;

import eggybyte.ip.data.task.Event;

public class EventCommand extends AddCommand {
    public static final String COMMAND_WORD = "event";
    protected static final int validArgumentAmount = 3;

    public EventCommand(String[] arguments) throws Exception {
        super(COMMAND_WORD, validArgumentAmount);
        checkArguments(arguments);
        task = new Event(arguments[0], arguments[1], arguments[2]);
    }
}
