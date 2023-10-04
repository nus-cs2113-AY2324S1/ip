package commands;

import java.util.Objects;

public class Parser {

    private static Ui ui;

    /**
     * Validates user input for various commands based on the specified method name.
     *
     * <p>This method performs input validation for different commands (e.g., "mark," "unmark," "todo," "find," "delete")
     * by checking the provided method name and the corresponding input parameters (words) against specific criteria.
     * It returns true if the input is valid for the specified method, and false otherwise. Additionally, it may display
     * error messages using the user interface (ui) for invalid inputs.
     * </p>
     *
     * @param methodName    The name of the method associated with the user command.
     * @param words         An array of words representing the input parameters.
     * @param numberOfItems The total number of items (e.g., tasks) currently in the list.
     * @return true if the input is valid; false otherwise.
     */
    public static boolean validateInput(String methodName, String[] words, int numberOfItems) {
        ui = new Ui();
        switch (methodName) {
        case "mark": {
            if (words.length == 1 || !words[1].matches("-?\\d+")) {
                ui.showError(DukeException.invalidInputForType(words[0]));
                return false;
            }
            System.out.println("mark " + words[1]);
            int index = Integer.parseInt(words[1]);
            // we need to check if it's out of bounds first
            if (index > numberOfItems || index <= 0) {
                ui.showError(DukeException.outOfBoundsError());
                return false;
            }
            break;
        }
        case "unmark": {
            if (words.length == 1 || !words[1].matches("-?\\d+")) {
                ui.showError(DukeException.invalidInputForType(words[0]));
                return false;
            }
            System.out.println("unmark " + words[1]);
            int index = Integer.parseInt(words[1]);
            // we need to check if it's out of bounds first
            if (index > numberOfItems || index <= 0) {
                ui.showError(DukeException.outOfBoundsError());
                return false;
            }
            break;
        }
        case "todo": {
            if (words.length == 1) {
                ui.showError("task missing");
                return false;
            }
            break;
        }
        case "find": {
            if (words.length == 1) {
                ui.showError("keyword missing for find function");
                return false;
            } else if (words.length > 2) {
                ui.showError("find function only works for a single keyword, not phrases");
                return false;
            }
            break;
        }
        case "delete": {
            if (words.length == 1 || !words[1].matches("-?\\d+")) {
                ui.showError(DukeException.invalidInputForType(words[0]));
                return false;
            }
            System.out.println("delete " + words[1]);
            int index = Integer.parseInt(words[1]);
            // we need to check if it's out of bounds first
            if (index > numberOfItems || index <= 0) {
                ui.showError(DukeException.outOfBoundsError());
                return false;
            }
            break;
        }
        default: {
            break;
        }
        }
        return true;
    }

    /**
     * Validates timed task input based on the specified method name and word array.
     *
     * <p>This method validates timed task input to ensure that it conforms to the expected
     * format for a given method. It checks the number of elements in the wordArray based on
     * the provided method name ("event" or "deadline"). If the input is valid, the method
     * returns true; otherwise, it displays an error message and returns false.
     * </p>
     *
     * <p>Validations:
     * <ul>
     *   <li>For "event" method: Expects three elements in wordArray.</li>
     *   <li>For "deadline" method: Expects two elements in wordArray.</li>
     *   <li>For other method names: Returns false and displays an "Unknown method" error.</li>
     * </ul>
     * </p>
     *
     * @param methodName The name of the method to validate ("event" or "deadline").
     * @param wordArray  An array of words representing the input for the timed task.
     * @return true if the input is valid; false otherwise.
     */
    public static boolean validateTimedTasks(String methodName, String[] wordArray) {
        switch (methodName) {
        case "event":
            if (wordArray.length != 3) {
                ui.showError("Invalid input for event function");
                return false;
            }
            break;
        case "deadline":
            if (wordArray.length != 2) {
                ui.showError("Invalid input for deadline function");
                return false;
            }
            break;
        default:
            // Handle other cases or show an error if methodName is unknown
            ui.showError("Unknown method: " + methodName);
            return false;
        }

        return true;
    }

}
