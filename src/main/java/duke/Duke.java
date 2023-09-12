package duke;

import java.util.Scanner;

import tasklist.TaskList;


/**
 *
 * The main program that controls all classes, inputs and outputs.
 */
public class Duke {

    public static TaskList taskList = new TaskList();

    public static void main (String[] args) {
        DukeUi ui = new DukeUi();
        Scanner scanner = new Scanner(System.in);

        ui.printWelcomeMessage();

        while (true) {
            String input = scanner.nextLine().trim();
            String[] inputWords = input.split(" ");
            String command = inputWords[0].toLowerCase();

            switch (command) {
            case "bye":
                return;
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
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
            }
        }
    }
}

