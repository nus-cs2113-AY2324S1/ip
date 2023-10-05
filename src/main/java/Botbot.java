import java.util.Scanner;

//Import packages and classes
import Commands.Command;
import Commands.ByeCommand;
import Task.TaskList;
import Parser.Parser;
import Storage.Storage;
import Ui.Ui;

import java.io.IOException;
import Exceptions.DukeException;


public class Botbot {
    /**
     * Main method of program.
     * @param args command-line arguments.
     * @throws IOException when file does not exist and new file cannot be created
     * @throws DukeException when format of saved file is wrong and hence cannot be loaded
     */
    public static void main(String[] args) throws DukeException, IOException {
        Ui.startBot();
        TaskList taskList = new TaskList();
        //load file
        Storage.loadListFromFile();

        //create new scanner object
        Scanner scanner = new Scanner(System.in);
        Command command;

         do{
            String input = scanner.nextLine();
            Ui.printLine();
            command = Parser.parse(input);
            command.execute();
            Ui.printLine();
         }while (!(command instanceof ByeCommand));

        Ui.endBot();
        scanner.close();
    }
}