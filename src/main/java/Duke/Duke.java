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
    private final static String IO_EXCEPTION_ERROR_PROMPT = "Please ensure that %s exists.";
    private final Ui ui;
    private final Storage storage;
    private final Parser parser;
    private final TaskList taskList;

    public Duke() {
        ui = new Ui();
        storage = new Storage(DIR_PATH, FILE_PATH);
        parser = new Parser(storage);
        taskList = new TaskList();
    }

    private void interactWithUser() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.trim().equals("bye")) {
            try {
                parser.generateResponse(line, taskList);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(PARAMETER_COUNT_ERROR_PROMPT);
            }
            line = in.nextLine();
        }
    }

    private void initialiseTaskList() {
        if (!storage.verifyStorageFilePresent()) {
            return;
        }

        storage.loadTaskList(taskList);

    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        try {
            ui.printWelcomeMessage();
            initialiseTaskList();
            interactWithUser();
            storage.saveTaskList(taskList);
            ui.printByeMessage();
        } catch (IOException e) {
            System.out.printf((IO_EXCEPTION_ERROR_PROMPT), FILE_PATH);
        }
    }
}

