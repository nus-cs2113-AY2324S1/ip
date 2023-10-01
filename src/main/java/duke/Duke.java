package duke;

import duke.exceptions.InvalidCommandException;

public class Duke {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;
    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.loadData());
    }

    public void run(){
        ui.introduceBot(tasks);
        handleCommands();
        ui.farewellBot();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void handleCommands() {
        String input;

        do {
            input = ui.readCommand();
            Command c = Parser.extractCommand(input);
            try {
                c.executeCommand(tasks, ui);
                storage.saveData(tasks.getTasks());
            } catch (InvalidCommandException e) {
                e.printErrorMessage();
            }
            ui.printHorizontalLine();
        } while (!input.equalsIgnoreCase("bye"));
    }

}
