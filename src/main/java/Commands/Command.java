package Commands;

import Data.TaskList;
import Data.Task;
import Data.Todo;
import Data.Event;
import Data.Deadline;
import Exceptions.CSGPTException;
import Exceptions.CSGPTParsingException;
import Ui.TextUi;

public abstract class Command {

    public abstract void execute(TaskList tasklist, TextUi ui) throws CSGPTException;
}
