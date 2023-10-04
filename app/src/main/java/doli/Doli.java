package doli;

import doli.GUI.Ui;
import doli.commands.Command;
import doli.commands.Parser;
import doli.exceptions.DoliExceptions;
import doli.files.Storage;
import doli.tasks.TaskList;

import java.io.IOException;

/**
 * <h1>Doli Chatbot</h1>
 * The Doli program implements a relatively simple chatbot that is able to
 * manage an agenda containing different types of tasks, including general
 * todos, deadlines and events.
 * <p>
 * <b>Note:</b> The user can interact with Doli using a number of commands
 * that e.g. allow adding, deleting, viewing, searching and marking individual
 * tasks as done.
 *
 * @author pappalardodaniel
 * @version 1.0
 * @since 2023-10-03
 */
public class Doli {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs an Object of type Doli by initialising a new
     * Storage, TaskList and Ui. The String filePath must specify
     * an OS-independent relative file path to a .txt file which
     * Doli uses to store and maintain the agenda. If no such file
     * is found, then a blank .txt file will be created.
     *
     * @param filePath of type String specifying the relative
     *                 file path giving the location of a .txt
     *                 file containing the older version of the agenda.
     */
    public Doli(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.initializeFile());
        } catch (DoliExceptions e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that runs the core of the chatbot.
     * It works in a sequential manner by first welcoming
     * the user and recording his/her name before entering a loop where
     * Doli will process and handle the user input by printing a
     * response which will also be stored in the initialised file path.
     * <p>
     * Once the user gives the command to halt the program,
     * the wile loop will stop and the program will exit.
     */
    public void run() {
        ui.welcomeUser();
        String name;
        try {
             name = ui.getName();
        } catch(DoliExceptions e) {
             name = "User:";
        }
        ui.askForInstruction();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.printUser(name);
                String fullCommand = ui.getInput();
                Command c = Parser.parseInputIntoCommand(fullCommand);
                c.handleCommand(tasks, ui, storage);
                c.getResponse();
                isExit = c.isExit();
            } catch (DoliExceptions e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printHLine();
            }
        }
    ui.greetUser();
    }
    /**
     * This is Doli's main method.
     * It simply constructs a new object of type Doli,
     * sets its file path to some relative .txt file and
     * then calls the run method on the initialisation.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Doli("Agenda.txt").run();
    }
}
