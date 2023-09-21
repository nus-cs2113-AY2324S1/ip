package Commands;

import Ui.TextUi;
import Data.TaskList;
public class Echo extends Command {
    private final String text;

    public Echo(String text) {
        this.text = text;
    }

    @Override
    public void execute(TaskList tasklist, TextUi ui) {
        ui.printText(text);
    }
}
