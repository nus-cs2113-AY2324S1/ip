package MySun;

import MySun.commands.Command;
import MySun.commands.ExitCommand;
import MySun.data.TaskList;
import MySun.data.exception.SunException;
import MySun.ui.Ui;
import MySun.storage.Storage;
import MySun.parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class MySun {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    public MySun(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readDataFromFile());
        } catch (FileNotFoundException e) {
            Ui.showReadDataError();
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
            tasks = new TaskList(storage.readDataFromFile());
        }
    }


    private void run() {
        Ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                Command command = Parser.parseCommand(fullCommand, tasks);
                command.execute(tasks);
                isExit = ExitCommand.isExit(command);
                storage.writeToFile(tasks);
            } catch (SunException | IndexOutOfBoundsException | ParseException e) {
                ui.showException(e);
            } catch (IOException e) {
                Ui.showLine();
                System.out.println("Something went wrong: " + e.getMessage());
                Ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new MySun("./data/MySun.txt").run();
    }
}

