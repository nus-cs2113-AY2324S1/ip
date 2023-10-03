package commands;

public class Parser {

    private static Ui ui;

    public static boolean parse(String methodName, String[] words, int numberOfItems){
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
            System.out.println("to do to be done");
            break;
        }
        case "deadline": {
            System.out.println("deadline to be done");
            break;
        }
        case "event": {
            System.out.println("event to be done");
            break;
        }
        case "delete": {
            System.out.println("delete to be done");
            break;
        }
        default: {
            System.out.println("invalid method name");
            break;
        }
        }
        return true;
    }

}
