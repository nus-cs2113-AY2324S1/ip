package Commands;

import Ui.Ui;
import Data.TaskList;
public class Echo extends Command {
    private final String text;

    public Echo(String text) {
        this.text = text;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui) {
        ui.printText(text);
    }
}
