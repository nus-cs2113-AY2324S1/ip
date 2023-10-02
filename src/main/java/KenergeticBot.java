import kenergeticbot.TaskList;
import kenergeticbot.command.Command;

import kenergeticbot.fileaccess.Storage;
import kenergeticbot.ui.TextUi;
import kenergeticbot.Parser;
import kenergeticbot.command.ExitCommand;


public class KenergeticBot {
    public static final String filePath = "data/KenergeticBot.txt";

    private TextUi ui;
    private Parser commandParser;
    private Storage storage;
    public TaskList taskList;

    public static void main(String[] args) {
        new KenergeticBot().run();
    }

    private void start(String filePath, TaskList taskList, TextUi ui) {
        this.storage = new Storage(filePath);
        storage.loadPreviousList(taskList);
        ui.printGreetingMessage();
    }

    public void run() {
        this.taskList = new TaskList();
        this.ui = new TextUi();
        start(filePath, taskList, ui);
        runCommandLoopUntilExitCommand();
        exit(ui);
    }
    public void runCommandLoopUntilExitCommand() {

        do {
            String item = ui.getUserCommand();
            Command c = Parser.parseCommand(taskList, item);
            c.execute(taskList, ui);
        } while (!ExitCommand.isExit());
        storage.saveList(taskList);
    }

    private static void exit(TextUi ui) {
        ui.printExitMessage();
        System.exit(0);
    }
}
