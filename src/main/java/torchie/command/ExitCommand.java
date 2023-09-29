package torchie.command;

import torchie.task.TaskList;
//import torchie.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
//        this.ui = new Ui();
    }

    @Override
    public void handleCommand() {
        System.out.println("Awww bye :( Let's play again soon!");
    }
}
