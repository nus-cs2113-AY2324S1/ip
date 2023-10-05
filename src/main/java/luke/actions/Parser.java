package luke.actions;

import luke.tasks.Deadline;
import luke.tasks.Event;
import luke.tasks.Task;
import luke.tasks.Todo;
import luke.user.LukeTimeError;

public class Parser {
    //user input from Ui to luke command to Command

    public static Command parse(String fullCommand) {
        ActionType theAction;
        String parameters = "";

        String[] words = fullCommand.split(" "); //to identify usage of features "mark" & "unmark"

        try {
            ActionType action = ActionType.valueOf(words[0].toUpperCase());
            theAction = action;

            try {
                switch (action) {
                    case LIST:
                        parameters = "";
                        break;

                    case FIND:
                        parameters = fullCommand.substring(4);
                        break;

                    case MARK: case UNMARK: case DELETE:
                        parameters = words[1];
                        break;

                    case TODO:
                        parameters = fullCommand.substring(4);
                        break;

                    case DEADLINE:
                        parameters = fullCommand.substring(8);
                        break;

                    case EVENT:
                        parameters = fullCommand.substring(5);
                        break;

                    default:
                        assert false: "This line should never be reached";
                        break;
                }
            } catch (IndexOutOfBoundsException e) { //empty for MARK, UNMARK, TO DO description, DEADLINE description, EVENT description
                System.out.println("\tOOPS!!! You have missing arguments for " + words[0] + ".");
            }
            Command c = new Command(theAction, parameters);
            return c;
        } catch (IllegalArgumentException e) {
            System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return null;

    }
}
