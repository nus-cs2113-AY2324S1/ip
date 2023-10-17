package Ken;

import java.time.LocalDateTime;

import Commands.Command;
import Commands.Goodbye;
import Exceptions.KenException;
import Exceptions.KenParsingException;
import Parser.ParseCommands;
import Storage.Storage;
import Tasks.TaskList;
import UI.Ui;

import java.util.Scanner;

/**
 * Ken is a Barbie-themed task manager that allows users to manage their tasks.
 * Users can add, delete, mark as done, list, and search for tasks.
 * Ken also provides a user-friendly text-based interface for interaction.
 */
public class Ken {
    private final TaskList list = new TaskList();

    /**
     * Executes the Barbie-themed task manager application.
     * It starts by greeting the user, loading tasks from a file and running the initial command.
     */
    public void execute() {
        Scanner scan = new Scanner(System.in);
        ParseCommands parser = new ParseCommands();

        Ui.greetUser();
        try {
            Storage.readFromFile(list);
            Command startCommand = parser.parse("list");
            startCommand.run(list);
        } catch (Exception e) {
            Ui.printTexts(new String[] {
                    e.getMessage()
            });
        }

        Command command = null;
        String input;
        while (!(command instanceof Goodbye)) {
            input = scan.nextLine();
            try {
                command = parser.parse(input);
                try {
                    command.run(list);
                } catch (KenParsingException e) {
                    Ui.printTexts(new String[] {
                            e.getMessage()
                    });
                }
            } catch (KenException e) {
                Ui.printTexts(new String[] {
                        e.getMessage()
                });
            }
        }
    }


    /**
     * Main method to run the program.
     * It creates a new instance of Ken and executes the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Ken().execute();
    }
}
