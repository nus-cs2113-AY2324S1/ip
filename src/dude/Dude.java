package dude;

import java.util.ArrayList;

public class Dude {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Dude(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFromFile(), storage, ui);
        } catch (Exception e) {
            // Display a generic error message or log the exception details
            ui.showMessage("Error loading tasks from file: " + e.getMessage());
            tasks = new TaskList(new ArrayList<>(), storage, ui);
        }
    }

    public void run() {
        ui.showGreeting();
        while (true) { // Infinite loop since exit is handled in the Parser
            try {
                String fullCommand = ui.readCommand();
                Parser.parse(fullCommand, tasks, ui, storage);
            } catch (DudeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Dude("data/dude.txt").run();
    }
}
