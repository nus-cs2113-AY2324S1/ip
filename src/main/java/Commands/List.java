package Commands;

import Data.TaskList;
import Ui.Ui;

public class List extends Command{

    @Override
    public void execute(TaskList list, Ui ui) {
        list.getTasks(ui);
    }
}
