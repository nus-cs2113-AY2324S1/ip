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
import Exceptions.DukeFormatException;
import Exceptions.DukeIndexException;


public class Botbot {
    //main method
    public static void main(String[] args) throws DukeException, IOException, DukeIndexException, DukeFormatException {
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