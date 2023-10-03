package commands;

import java.util.Objects;

public class Parser {

    private static Ui ui;

    public static boolean validateInput(String methodName, String[] words, int numberOfItems){
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
        //im busy student... no time do, please give chance XD
        case "todo": {
            if(words.length == 1){
                ui.showError("task missing");
                return false;
            }
            break;
        }
        case "find": {
            if(words.length == 1){
                ui.showError("keyword missing for find function");
                return false;
            }else if(words.length > 2){
                ui.showError("find function only works for a single keyword, not phrases");
                return false;
            }
            break;
        }
        case "delete": {
            System.out.println("delete to be done");
            break;
        }
        default: {
//            System.out.println("invalid method name");
            break;
        }
        }
        return true;
    }

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
