/**
 * The main class representing the chatbot.
 */

package Chatty;

import Chatty.Command.*;

public class Chatty {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Chatty (String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }
    public void run() {
        ui.printWelcomeMessage();
        String fullCommand = ui.getUserInput();
        while (!fullCommand.equalsIgnoreCase("bye")) {
            try {
                ui.printMessage(Ui.LINE);
                Command c = Parser.parseCommand(fullCommand, tasks);
                c.execute(tasks, ui, storage);
            } catch (Exception e) {
                ui.printMessage(e.getMessage());
            } finally {
                ui.printMessage(Ui.LINE);
            }
            fullCommand = ui.getUserInput();
        }
        storage.save(tasks);
        ui.printGoodbyeMessage();
    }

    public static void main(String[] args) {
        new Chatty("./data/chatty.txt").run();
    }
}
