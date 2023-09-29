package Duke;

import Duke.Command.Command;
import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.Task.TaskList;
import Duke.Ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

/**
 * Starts up the application.
 */
public class Duke {

    private final static String FILE_PATH = "data/duke.txt";
    private final static String DIR_PATH = "data";
    private final static String PARAMETER_COUNT_ERROR_PROMPT = "Please ensure correct number of parameters are given.";
    private final Ui ui;
    private final Storage storage;
    private final Parser parser;
    private final Command command;
    private final TaskList tasks1;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        command = new Command();
        tasks1 = new TaskList();
    }

    private void interactWithUser() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.trim().equals("bye")) {
            try {
                parser.generateResponse(line, tasks1);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(PARAMETER_COUNT_ERROR_PROMPT);
            }
            line = in.nextLine();
        }
    }

    private void initialiseTaskList() {
        File file = new File(Duke.FILE_PATH);
        File directory = new File(Duke.DIR_PATH);
        if (!storage.verifyStorageFilePresent(directory, file)) {
            return;
        }
        Scanner s;
        try {
            s = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        storage.loadTaskList(s, tasks1);

    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        try {
            ui.printWelcomeMessage();
            initialiseTaskList();
            interactWithUser();
            command.saveTaskList(FILE_PATH, tasks1);
            ui.printByeMessage();
        } catch (IOException e) {
            System.out.println("Please ensure that " + Duke.FILE_PATH + " exists.");
        }
    }
}

