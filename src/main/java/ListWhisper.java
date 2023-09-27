import command.Command;
import parser.Parser;
import task.TaskList;
import storage.Storage;
import ui.Ui;
import java.io.IOException;
import java.lang.String;

public class ListWhisper {
    private final Ui ui;
    private final Parser parser;
    TaskList taskList;

    public static void main(String[] args) {
        new ListWhisper().run();
    }

    public ListWhisper() {
        this.ui = new Ui();
        this.parser = new Parser();
        Storage storage = new Storage(this.ui);

        try {
            this.taskList = storage.load(this.parser);
        } catch (IOException e) {
            Ui.showError(e);
            taskList = new TaskList();
        }
    }

    public void run() {
        Ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                Ui.showLine();
                Command c = this.parser.parse(fullCommand);
                c.execute(taskList, ui);
                isExit = c.isExit();
            } catch (Exception e) {
                Ui.showError(e);
            } finally {
                Ui.showLine();
            }
        }
    }
}