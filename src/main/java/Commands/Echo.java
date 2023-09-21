package Commands;

import Ui.Ui;
import Data.TaskList;
/**
 * Command to echo a message
 */
public class Echo extends Command {
    private final String text;

    /**
     * Constructor for Echo command
     * @param text Text to be echoed
     */
    public Echo(String text) {
        this.text = text;
    }

    /**
     * Executes the command to echo a message
     * @param tasklist Tasklist due to inheritance
     * @param ui Ui to print messages
     */
    @Override
    public void execute(TaskList tasklist, Ui ui) {
        ui.printText(text);
    }
}
