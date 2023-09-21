package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import tasklist.TaskList;


/**
 *
 * The main program that controls all classes, inputs and outputs.
 */
public class Duke {

    public static TaskList taskList = new TaskList();

    private static boolean enteredBye = false;

    public static void main (String[] args) {
        DukeUi ui = new DukeUi();
        Scanner scanner = new Scanner(System.in);

        try {
            FileManager.createNewFileDirectory();
            FileManager.createNewSaveFile();
        } catch (IOException e) {
            System.out.println("Error creating directory and file.");
            e.printStackTrace();
        }

        try {
            FileManager.printFileContents();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            taskList = FileManager.parseSavefile();
        } catch (IOException e) {
            System.out.println("File cannot be found");
        } catch (NullPointerException e) {
            System.out.println("Nothing in list");
        }

        ui.printWelcomeMessage();

        do {
            String input = scanner.nextLine().trim();
            String[] inputWords = input.split(" ");
            String command = inputWords[0].toLowerCase();

            switch (command) {
            case "bye":
                enteredBye = true;
                break;
            case "list":
                ui.printTaskList();
                break;
            case "todo":
                taskList.addToDoTask(input, inputWords);
                break;
            case "deadline":
                taskList.addDeadline(input, inputWords);
                break;
            case "event":
                taskList.addEvent(input, inputWords);
                break;
            case "mark":
                taskList.markAsDone(inputWords);
                break;
            case "unmark":
                taskList.unmark(inputWords);
                break;
            case "delete":
                taskList.deleteTask(inputWords);
                break;
            default:
                ui.printLine();
                System.out.println("☹ OOPS!!! I'm sorry, " +
                        "but I don't know what that means :-(");
                ui.printLine();
                break;
            }
        } while (!enteredBye);

        try {
            FileManager.writeToFile(taskList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

