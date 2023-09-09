package Commands;

import CSGPT.CSGPT;
import CSGPT.TaskList;
public class Echo extends Command {
    private final String text;

    public Echo(String text) {
        this.text = text;
    }

    @Override
    public void execute(TaskList tasklist) {
        CSGPT.printText(text);
    }
}
