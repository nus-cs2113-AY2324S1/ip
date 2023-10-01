package Commands;

import Data.TaskList;
import Ui.Ui;
public class Find extends Command {
    private final String keyword;

    public Find(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui) {
        tasklist.getTasks(ui, keyword);
    }
}
