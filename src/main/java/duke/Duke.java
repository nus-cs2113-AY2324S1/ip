package duke;

import duke.exceptions.InvalidCommandException;

/**
 * Main class of the Duke program.
 * Duke is a chatbot that helps the user to keep track of tasks.
 * The user can add tasks, mark tasks as done, delete tasks and view the list of tasks.
 * The bot supports three types of tasks: Todo, Deadline and Event.
 * The tasks are saved in a file and loaded automatically from the file when the program is started again.
 * The file is saved in the data folder.
 * The user can exit the program by typing "bye".
 * */
public class Duke {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor of Duke. Initializes the Ui, Storage and Tasks list.
     * @parm filePath Path to the file where the data is stored. Default path is data/duke.txt.
     * */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadData());
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        this.ui.introduceBot(tasks);
        this.handleCommands();
        this.ui.farewellBot();
    }

    /**
     * Main method of the Duke program.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Handles the user interaction loop and executes the commands.
     * Method will continue to ask for user input until its termination on the "bye" command.
     * @see Command
     */
    public void handleCommands() {
        String input;
        do {
            input = this.ui.readCommand();
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
