package chatbot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exception.FattyException;
import tasks.Task;

/**
 * This is the chatbot class. This contains an array of Task objects and contains functions to handle them.
 */

public class Duke {
    public static ArrayList<Task> taskList;
    private static Storage storage;
    private static Ui ui;
    private static Parser parser;

    public Duke() {
        taskList = new ArrayList<>();
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
    }

    /**
     * Main function creates new Duke object and calls run method
     * @param args
     * @throws FattyException
     */
    public static void main(String[] args) throws FattyException, IOException {
        new Duke().run();
    }

    /**
     * Run function executes the main while loop of the program
     * @throws FattyException
     */
    public static void run() throws FattyException, IOException {

        try {
            storage.load();
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        ui.printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);

        while(true) {
            String inputMessage = scanner.nextLine();

            if (inputMessage.equals("bye")) {
                ui.printGoodbyeMessage();
                break;
            }

            try {
                String keyword = parser.getKeyword(inputMessage);

                switch (keyword) {
                    case "list":
                        list();
                        break;
                    case "mark":
                        mark(parser.getIndex(inputMessage));
                        break;
                    case "unmark":
                        unmark(parser.getIndex(inputMessage));
                        break;
                    case "delete":
                        delete(parser.getIndex(inputMessage));
                        break;
                    case "find":
                        find(parser.getSearchField(inputMessage));
                        break;
                    case "create":
                        Task newTask = parser.getTask(inputMessage);
                        taskList.add(parser.getTask(inputMessage));
                        ui.printNewTaskMessage();
                        newTask.show();
                        ui.printDivider();
                        break;
                }

                storage.save();

            } catch (FattyException e) {
                ui.printDivider();
                System.out.println(e.toString());
                ui.printDivider();
                continue;
            }
        }
    }

    private static void add(Task task) {
        taskList.add(task);
    }

    private static void delete(int index) throws FattyException {
        if(index <= 0 || index > taskList.size()) {
            throw new FattyException ("Please enter a valid index");
        }

        ui.printDeleteMessage();
        taskList.get(index - 1).show();
        ui.printDivider();

        taskList.remove(index - 1);
    }

    private static void list() {
        ui.printListMessage();

        for(int i = 0; i < taskList.size(); i++) {
            taskList.get(i).show();
        }

        ui.printDivider();
    }

    private static void mark(int index) throws FattyException {
        if(index <= 0 || index > taskList.size()) {
            throw new FattyException ("Please enter a valid index");
        }

        taskList.get(index - 1).mark();
    }

    private static void unmark(int index) throws FattyException {
        if(index <= 0 || index > taskList.size()) {
            throw new FattyException ("Please enter a valid index");
        }

        taskList.get(index - 1).unmark();
    }

    private static void find(String keyword) {
        boolean found = false;

        for(int i = 0; i < taskList.size(); i++) {
            if(taskList.get(i).getDescription().contains(keyword)) {
                taskList.get(i).show();
                found = true;
            }
        }

        if(found) {
            ui.printFoundMessage();
        } else {
            ui.printNotFoundMessage();
        }
    }
}
