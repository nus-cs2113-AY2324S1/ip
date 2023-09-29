package RC;

import RC.UI.Ui;
import RC.command.Exit;
import RC.command.Parser;
import RC.command.RCCommand;
import RC.storage.Storage;

/**
 * The main class representing RC chatbot.
 * It manages tasks and user interactions.
 */
public class RC {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs an RC instance and loads file if it exists, otherwise
     * create a new file.
     *
     * @param filePath The file path where data is stored and retrieved.
     */
    public RC(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, ui);
        taskList = new TaskList();

        try {
            storage.load(taskList, ui);
        } catch (RCException e) {
            taskList = new TaskList();
            ui.showMessage(e.getMessage());
        }
    }

    /**
     * Starts the RC chatbot and handles user interaction.
     * Saves storage when user exits chatbot.
     */
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

    /**
     * Saves current taskList to storage.
     */
    private void save() {
        try {
            storage.save(taskList);
        } catch (RCException e) {
            ui.showMessage(e.getMessage());
        }
    }

    /**
     * Main starting point of RC chatbot.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new RC("data").run();
    }
}
