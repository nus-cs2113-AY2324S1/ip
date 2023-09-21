package Commands;

import Data.TaskList;
import Exceptions.CSGPTException;
import Ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasklist, Ui ui) throws CSGPTException;
}
