import Command.Command;
import Soccat.SoccatException;
import Parser.Parser;
import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Soccat {

    private TaskList tasks;
    static final String FILENAME = "data/Soccat.txt";
    private Storage taskFile;
    private Ui ui;

    public Soccat(String fileName) {
        this.ui = new Ui();
        try {
            this.taskFile = new Storage(fileName);
            this.tasks = new TaskList(taskFile.getTaskData());
        } catch (SoccatException e) {
            ui.displayError(ui.FILE_CREATION_ERROR + fileName);
        } catch (IOException e) {
            ui.displayError(ui.IO_EXCEPTION_MESSAGE);
        }
    }

    public void run() {
        ui.displayWelcome();
        Scanner textIn = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String commandInput = textIn.nextLine();
            Command command = new Parser().parseCommand(commandInput);
            isExit = command.execute(tasks, ui, taskFile);
            ui.displayLine();
        }
    }

    public static void main(String[] args) {
        new Soccat(FILENAME).run();
    }
}
