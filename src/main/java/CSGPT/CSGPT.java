package CSGPT;

import java.util.Scanner;
import Commands.Command;
import Commands.Echo;
import Commands.Farewell;
import Data.TaskList;
import Exceptions.CSGPTException;
import Exceptions.CSGPTParsingException;
import Exceptions.CSGPTWriteFileException;

import Parser.Parser;
import Storage.Storage;
import Ui.Ui;

/**
 * Main class for CSGPT
 */
public class CSGPT {
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();
    private final Ui ui = new Ui();

    /**
     * Main method to run the program
     */
    public void run() {
        String input;
        Command command = new Echo("");
        Scanner in = new Scanner(System.in);

        ui.greet();
        try {
            storage.readFromFile(taskList);
            Command listCommand = Parser.getCommand("list");
            listCommand.execute(taskList, ui);
        } catch (Exception e) {
            ui.printText(e.getMessage());
        }

        while(!(command instanceof Farewell)) {
            input = in.nextLine();
            try {
                command = Parser.getCommand(input);
                try {
                    command.execute(taskList, ui);
                    try {
                        storage.writeToFile(taskList);
                    } catch (CSGPTWriteFileException e) {
                        ui.printText(e.getMessage());
                    }
                } catch (CSGPTParsingException e) {
                    ui.printText(e.getMessage());
                }
            } catch (CSGPTException e) {
                ui.printText(e.getMessage());
            }
        }
    }

    /**
     * Main method to run the program
     * @param args Arguments
     */
    public static void main(String[] args) {
        new CSGPT().run();
    }
}
