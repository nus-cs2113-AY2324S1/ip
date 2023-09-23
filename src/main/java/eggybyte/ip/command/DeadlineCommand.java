package eggybyte.ip.command;

import eggybyte.ip.data.task.Deadline;

public class DeadlineCommand extends AddCommand {
    public static final String COMMAND_WORD = "deadline";
    protected static final int validArgumentAmount = 2;

    public DeadlineCommand(String[] arguments) throws Exception {
        super(COMMAND_WORD, validArgumentAmount);
        checkArguments(arguments);
        task = new Deadline(arguments[0], arguments[1]);
    }
}
