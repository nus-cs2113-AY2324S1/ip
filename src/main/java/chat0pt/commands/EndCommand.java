package chat0pt.commands;

import chat0pt.tasks.TaskList;
import chat0pt.ui.Ui;

public class EndCommand extends Command {
    /**
     * Executed when the user enters 'Bye'
     * @param ui Used for printing
     * @param tasks Current tasklist (not used here)
     */
    @Override
    public void runCommand(Ui ui, TaskList tasks){
        endProgram = true;
    }
}
