package CSGPT;

import java.util.Scanner;
import Commands.Command;
import Commands.Echo;
import Commands.Farewell;
import Exceptions.CSGPTException;
import Exceptions.CSGPTParsingException;
import Exceptions.CSGPTWriteFileException;

import Ui.TextUi;

public class CSGPT {
    private static final TaskList taskList = new TaskList();

    public static void main(String[] args) {
        String input;
        Command command = new Echo("");
        Scanner in = new Scanner(System.in);

        TextUi.greet();
        try {
            FileHandler.readFromFile(taskList);
            Command listCommand = Command.getCommand("list");
            listCommand.execute(taskList);
        } catch (Exception e) {
            TextUi.printText(e.getMessage());
        }

        while(!(command instanceof Farewell)) {
            input = in.nextLine();
            try {
                command = Command.getCommand(input);
                try {
                    command.execute(taskList);
                    try {
                        FileHandler.writeToFile(taskList);
                    } catch (CSGPTWriteFileException e) {
                        TextUi.printText(e.getMessage());
                    }
                } catch (CSGPTParsingException e) {
                    TextUi.printText(e.getMessage());
                }
            } catch (CSGPTException e) {
                TextUi.printText(e.getMessage());
            }
        }
    }
}
