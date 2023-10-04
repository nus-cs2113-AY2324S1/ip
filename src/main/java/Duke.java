import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            taskList = new TaskList();
        }
    }
    public void run() {
       Parser parser = new Parser();
       ui.showStartMessage();
       boolean isExit = false;
       while (!isExit) {
           try {
               String fullCommand = ui.readCommand();
               Command c = parser.parse(fullCommand);
               c.execute(taskList, ui, storage);
               isExit = c.isExit();
           } catch (DukeException e) {
               ui.showError(e);
           }
       }
       System.exit(0);
    }

    public static void main(String[] args) {
        new Duke("./data/data.txt").run();
    }

}
