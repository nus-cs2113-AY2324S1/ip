package commands;

import UI.Ui;
import data.Storage;
import exception.SimonException;
import task.TaskList;

public class Parser {
    public Parser() {

    }
    public static void parse(String userInput, TaskList tasks, Ui ui, Storage storage) {
        String[] splitInputs = userInput.split(" ", 2);
        switch (splitInputs[0]) {

        case "list":
            ui.printList(tasks.getTaskList(), ui);
            break;

        case "mark":
            tasks.markTask(splitInputs[1], ui);
            storage.markTask(splitInputs[1], tasks.getTaskList());
            break;

        case "unmark":
            tasks.unmarkTask(splitInputs[1], ui);
            storage.unmarkTask(splitInputs[1], tasks.getTaskList());
            break;

        case "delete":
            tasks.deleteTask(splitInputs[1], ui);
            storage.deleteTask(splitInputs[1], tasks.getTaskList());
            break;

        case "todo":
            try {
                tasks.addTodo(splitInputs[1], ui);
            } catch (IndexOutOfBoundsException | SimonException e) { //Empty description
                ui.printLine();
                System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty.");
                ui.printLine();
            }
            break;

        case "event":
            try {
                tasks.addEvent(splitInputs[1], ui);
            } catch (IndexOutOfBoundsException | SimonException e) { //Empty description
                ui.printLine();
                System.out.println("\t☹ OOPS!!! The description of an event cannot be empty.");
                ui.printLine();
            }
            break;

        case "deadline":
            try {
                tasks.addDeadline(splitInputs[1], ui);
            } catch (IndexOutOfBoundsException | SimonException e) { //Empty description
                ui.printLine();
                System.out.println("\t☹ OOPS!!! The description of a deadline cannot be empty.");
                ui.printLine();
            }
            break;

        case "bye":
            ui.printFarewell();
            break;

        default:
            //If unable to understand user input
            ui.printUnknownInputMessage();
        }
    }
}
