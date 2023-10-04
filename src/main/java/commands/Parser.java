package commands;

import UI.Ui;
import data.Storage;
import exception.SimonException;
import exception.SimonException2;
import task.TaskList;

/**
 * Class containing methods to make sense of the user command
 */
public class Parser {
    public Parser() {

    }

    /**
     * Takes in the user input from the command line and decides what the next course of
     * action is based on certain key words
     *
     * @param userInput User input from command line
     * @param tasks Contains the task list and its operations
     * @param ui Deals with interactions with the user
     * @param storage Deals with loading tasks from the file and saving tasks in the file
     */
    public static void parse(String userInput, TaskList tasks, Ui ui, Storage storage) {
        String[] splitInputs = userInput.split(" ", 2);
        switch (splitInputs[0]) {

        case "list":
            ui.printList(tasks.getTaskList());
            break;

        case "find":
            try {
                tasks.printMatchingTasks(splitInputs[1], ui);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printEmptyDescriptionError(splitInputs[0]);
            }
            break;

        case "mark":
            try {
                tasks.markTask(splitInputs[1], ui);
                storage.markTask(splitInputs[1], tasks.getTaskList());
            } catch (NumberFormatException e) {
                ui.printNumberFormatError();
            }
            break;

        case "unmark":
            try {
                tasks.unmarkTask(splitInputs[1], ui);
                storage.unmarkTask(splitInputs[1], tasks.getTaskList());
            } catch (NumberFormatException e) {
                ui.printNumberFormatError();
            }
            break;

        case "delete":
            try {
                tasks.deleteTask(splitInputs[1], ui);
                storage.deleteTask(splitInputs[1], tasks.getTaskList());
            } catch (NumberFormatException e) {
                ui.printNumberFormatError();
            }
            break;

        case "todo":
            try {
                tasks.addTodo(splitInputs[1], ui);
                storage.addTodo(splitInputs[1], tasks.getTaskList());
            } catch (IndexOutOfBoundsException | SimonException e) { //Empty description
                ui.printEmptyDescriptionError(splitInputs[0]);
            }
            break;

        case "event":
            try {
                tasks.addEvent(splitInputs[1], ui);
                storage.addEvent(splitInputs[1], tasks.getTaskList());
            } catch (IndexOutOfBoundsException | SimonException e) { //Empty description
                ui.printEmptyDescriptionError(splitInputs[0]);
            } catch (SimonException2 f) { //Wrong format
                ui.printEventFormat();
            }
            break;

        case "deadline":
            try {
                tasks.addDeadline(splitInputs[1], ui);
                storage.addDeadline(splitInputs[1], tasks.getTaskList());
            } catch (IndexOutOfBoundsException | SimonException e) { //Empty description
                ui.printEmptyDescriptionError(splitInputs[0]);
            } catch (SimonException2 f) { //Wrong format
                ui.printDeadlineFormat();
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
