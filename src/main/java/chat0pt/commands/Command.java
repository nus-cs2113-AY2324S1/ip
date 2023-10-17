package chat0pt.commands;

import chat0pt.helper.DukeException;
import chat0pt.tasks.TaskList;
import chat0pt.ui.Ui;

public abstract class Command {
    protected boolean endProgram = false;

    public abstract void runCommand(Ui ui, TaskList tasks) throws DukeException;

    public boolean getProgramStatus() {
        return endProgram;
    }
}
