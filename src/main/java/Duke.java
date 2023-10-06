import exception.DukeException;

import utils.*;

public class Duke {
    private final String FILE_PATH = "./data/list.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        parser = new Parser();
        tasks = new TaskList(storage.loadSave());
    }

    /*
     * Runs the CLI Interface
     */
    private void run() {
        boolean isExit = false;

        while(!isExit) {
            String in = ui.readCommand();
            try {
                parser.parseAndExecute(in, tasks, ui, storage);
                isExit = parser.isExit(in);
            } catch (DukeException e) {
                ui.echo(e.getErrorMessage());
            }
        }
    }


    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.run();
    }
}
