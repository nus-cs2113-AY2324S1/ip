package RC;

import RC.UI.Ui;
import RC.command.Exit;
import RC.command.Parser;
import RC.command.RCCommand;
import RC.storage.Storage;

public class RC {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public RC(String filePath) {
        ui = new Ui();
        this.storage = new Storage(filePath, ui);
        taskList = new TaskList();

        try {
            storage.load(taskList, ui);
        } catch (RCException e) {
            taskList = new TaskList();
            ui.showMessage(e.getMessage());
        }
    }

    public void run() {
        ui.welcomeMessage();
        RCCommand command = null;

        while (!(command instanceof Exit)) {
            String input = ui.input();
            try {
                command = Parser.parse(input);
                command.execute(taskList, ui);
            } catch (RCException e) {
                ui.showMessage(e.getMessage());
            }
        }

        save();
        ui.exitMessage();
    }

    private void save() {
        try {
            storage.save(taskList);
        } catch (RCException e) {
            ui.showMessage(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new RC("data").run();
    }
}
