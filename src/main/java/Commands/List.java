package Commands;

import Data.TaskList;
import Ui.TextUi;

public class List extends Command{

    @Override
    public void execute(TaskList list, TextUi ui) {
        list.getTasks(ui);
    }
}
