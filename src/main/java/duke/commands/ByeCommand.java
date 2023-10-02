package duke.commands;

/**
 * Represents a command to exit Duke.
 */
public class ByeCommand extends Command {

    /**
     * Returns whether the ByeCommand is an exit command.
     *
     * @return True if the ByeCommand is an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}