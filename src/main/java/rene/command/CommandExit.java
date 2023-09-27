package rene.command;
/**
 * Represents a command to exit the program.
 */
public class CommandExit extends Command {
    /**
     * Creates a new exit command to terminate program.
     */
    public CommandExit() {
        super(CommandType.EXIT);
    }
}
