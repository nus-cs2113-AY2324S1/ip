package chattie;

import chattie.commands.Command;
import chattie.error.ChattieException;

import java.io.IOException;

public class Chattie {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor that initializes a new Ui, Storage and TaskList object
     *
     * @throws ChattieException If there are any exceptions thrown while loading chattie.tx
     */
    public Chattie(String folderPath, String filePath) {
        ui = new Ui();
        storage = new Storage(folderPath, filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ChattieException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main function of the chatbot like reading commands and exiting the
     * chatbot when user types "bye"
     *
     * @throws ChattieException If there are any exceptions related to user command
     * @throws IOException If chattie.txt cannot be updated
     */
    public void run() {
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                storage.updateFile(tasks.getList());
                isExit = c.isExit();
            } catch (ChattieException e) {
                ui.showError();
            } catch (IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new Chattie("./data", "./data/chattie.txt").run();
    }
}
