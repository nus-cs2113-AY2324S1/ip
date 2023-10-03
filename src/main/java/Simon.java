import UI.Ui;
import data.Storage;
import exception.*;
import task.*;
import commands.Parser;
import java.util.Scanner;
import static data.Loader.readToList;
import static data.SimonFilePath.simontxtFilePath;


public class Simon {
    String userDirectory = System.getProperty("user.dir");
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    public Simon(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (SimonException e) {
            ui.printMissingFileError();
            storage.createSimonTxt(userDirectory);
            tasks = new TaskList(readToList(filePath));
        }
    }

    public void run() {
        String userInput = "";

        //Print out greeting when user starts the program.
        ui.printGreeting();

        //Take in user input
        Scanner in = new Scanner(System.in);
        while (!userInput.equals("bye")) {
            userInput = in.nextLine();
            ui.printLine();
            Parser.parse(userInput, tasks, ui, storage);
            ui.printLine();
        }
    }
    public static void main(String[] args) {
        new Simon(simontxtFilePath).run();
    }
}