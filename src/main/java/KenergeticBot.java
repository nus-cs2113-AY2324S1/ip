import kenergeticbot.TaskList;
import kenergeticbot.command.Command;

import java.util.Scanner;

import kenergeticbot.fileaccess.Storage;
import kenergeticbot.ui.TextUi;
import kenergeticbot.command.Parser;
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

    private void start(String filePath, TaskList taskList) {
        this.storage = new Storage(filePath);
        storage.loadPreviousList(taskList);
        TextUi.printGreetingMessage();
    }

    public void run() {
        this.taskList = new TaskList();
        start(filePath, taskList);
        runCommandLoopUntilExitCommand();
        exit();
    }
    public void runCommandLoopUntilExitCommand() {
        Scanner input = new Scanner(System.in);
        do {
            String item = input.nextLine();
            Command c = Parser.parseCommand(taskList, item);
            c.execute(taskList);
        } while (!ExitCommand.isExit());
        storage.saveList(taskList);
    }

    private static void exit() {
        TextUi.printExitMessage();
        System.exit(0);
    }
}
