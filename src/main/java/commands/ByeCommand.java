package commands;

import ui.Ui;

public class ByeCommand extends Command{
    @Override
    public void handleCommand() {
        Ui.printByeMessage();
        Ui.printLineDivider();
    }
}
