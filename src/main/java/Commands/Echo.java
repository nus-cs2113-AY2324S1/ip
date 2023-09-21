package Commands;

import Ui.TextUi;
import CSGPT.TaskList;
public class Echo extends Command {
    private final String text;

    public Echo(String text) {
        this.text = text;
    }

    @Override
    public void execute(TaskList tasklist) {
        TextUi.printText(text);
    }
}
