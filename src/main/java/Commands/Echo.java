package Commands;

import CSGPT.CSGPT;
public class Echo extends Command {
    private final String text;

    public Echo(String text) {
        this.text = text;
    }

    @Override
    public void execute() {
        CSGPT.printText(text);
    }
}
