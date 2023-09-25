package Ken;

import Commands.Command;
import Commands.Goodbye;
import Exceptions.KenException;
import Exceptions.KenParsingException;
import Parser.ParseCommands;
import Storage.Storage;
import Tasks.TaskList;
import UI.Ui;

import java.util.Scanner;

public class Ken {
    private final TaskList list = new TaskList();

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

    public static void main(String[] args) {
        new Ken().execute();
    }
}
