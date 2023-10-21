import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import commands.*;

import controller.Controller;

public class Duke {
    private static final String dukeDataFile = "./duke.txt";

    private static Storage storage;

    private static TaskList tasks;

    private static Ui ui;

    private static Controller controller;


    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.getTaskData());
        } catch (IOException e) {
            System.out.println("File not found");
            tasks = new TaskList(new ArrayList<>());
        }

        storage.addTaskSize(tasks.size());
        controller = new Controller(tasks,storage);

    }

    public void run() throws IOException {

        ui.showWelcome();

        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();

        while (!userInput.equals("Bye")) {

            String[] words = userInput.split(" ");

            String initialWord = words[0];

            boolean validWord = Parser.validateInput(initialWord, words, tasks.size());

            if (validWord) {
                switch (initialWord) {
                case "list": {
                    tasks.printList();
                    break;
                }

                case "delete": {
                    controller.delete(words);
                    break;
                }

                case "find": {
                    //only a single keyword
                    controller.find(words);
                    break;
                }

                case "mark": {
                    controller.mark(words);
                    break;
                }
                case "unmark": {
                    controller.unmark(words);
                    break;

                }
                case "todo": {
                    controller.todo(userInput);
                    break;
                }
                case "deadline": {
                    controller.deadline(userInput);
                    break;

                }
                case "event": {
                    controller.event(userInput);
                    break;

                }
                default: {
                    System.out.println("Invalid input, valid task adding commands are: event, deadline and todo");
                    break;
                }
                }
            }

            userInput = scanner.nextLine();

        }

        ui.showGoodbye();

        // Close the scanner when you're done with it
        scanner.close();
    }

    public static void main(String[] args) throws IOException {

        new Duke(dukeDataFile).run();

    }
}
