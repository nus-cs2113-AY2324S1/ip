import common.Command;
import listWhisper.task.Parser;
import listWhisper.task.TaskList;
import storage.DataManager;
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
        DataManager dataManager = new DataManager(this.ui);

        try {
            this.taskList = dataManager.load(this.parser);
        } catch (IOException e) {
            Ui.showError(e);
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                ui.showLine();
                Command c = this.parser.parse(fullCommand);
                c.execute(taskList, ui);
                isExit = c.isExit();
            } catch (Exception e) {
                Ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }
}