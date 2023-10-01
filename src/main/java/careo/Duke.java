package careo;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);


        try {
            tasks = new TaskList(storage.load(), ui);
        } catch (StorageLoadException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<Task>(), ui);
        }

        parser = new Parser(ui, tasks);
    }

    public void run() {
        ui.printIntroduction();

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean shouldTerminate = false;

        while (!shouldTerminate) {
            input = scanner.nextLine();

            try {
                shouldTerminate = parser.parseInput(input);
            } catch (Exception e) {
                ui.showInvalidInputError();
            }
        }

        ui.printFarewell();

        storage.save(tasks);
    }

    public static void main(String[] args) {
        new Duke("data.temp").run();
    }
}