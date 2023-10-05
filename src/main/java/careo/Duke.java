package careo;


import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    /** Central storage used for persistence of tasks */
    private Storage storage;
    /** Used for storing and querying user tasks */
    private TaskList tasks;
    /** UI that is responsible for user input and output */
    private Ui ui;
    /** Parser containing the business logic for processing user inputs */
    private Parser parser;

    /**
     * Instantiates a Duke instance with access to a file of persisted tasks.
     *
     * @param filePath The path of the file used for persistence.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);


        try {
            tasks = new TaskList(storage.load(), ui);
        } catch (StorageLoadException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<Task>(), ui);
        }

        parser = new Parser(ui, tasks);
    }

    /**
     * Starts an interactive REPL session for the user.
     */
    public void run() {
        ui.printIntroduction();

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean shouldTerminate = false;

        while (!shouldTerminate) {
            input = scanner.nextLine();

            try {
                shouldTerminate = parser.parseInput(input);
            } catch (Exception e) {
                ui.showInvalidInputError();
            }
        }

        ui.printFarewell();

        storage.save(tasks, ui);
    }

    public static void main(String[] args) {
        new Duke("data.temp").run();
    }
}