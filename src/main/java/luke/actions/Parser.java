package luke.actions;

import luke.tasks.Deadline;
import luke.tasks.Event;
import luke.tasks.Task;
import luke.tasks.Todo;
import luke.user.LukeTimeError;

public class Parser{
    //user input from Ui to luke command to Command

    public static Command parse(String fullCommand) throws LukeTimeError {
        ActionType theAction;
        String parameters = "";

        String[] words = fullCommand.split(" "); //to identify usage of features "mark" & "unmark"
        Command c = null;
        try {
            ActionType action = ActionType.valueOf(words[0].toUpperCase());
            theAction = action;
            //Command c = null;

            try {
                switch (action) {
                    case LIST:
                        parameters = "";
                        c = new ListCommand(theAction, parameters);
                        break;

                    case FIND:
                        parameters = fullCommand.substring(4);
                        c = new FindCommand(theAction, parameters);
                        break;

                    case MARK: case UNMARK:
                        parameters = words[1];
                        c = new MarkCommand(theAction, parameters);
                        break;

                    case TODO:
                        parameters = fullCommand.substring(5);
                        c = new AddCommand(theAction, parameters);
                        break;

                    case DEADLINE:
                        parameters = fullCommand.substring(9);
                        c = new AddCommand(theAction, parameters);
                        break;

                    case EVENT:
                        parameters = fullCommand.substring(6);
                        c = new AddCommand(theAction, parameters);
                        break;

                    case DELETE:
                        parameters = words[1];
                        c = new DeleteCommand(theAction, parameters);
                        break;

                    case BYE:
                        parameters = "";
                        c = new ExitCommand(theAction, parameters);
                        break;

                    default:
                        c = new AddCommand(theAction, "");
                        assert false: "This line should never be reached";
                        break;
                }
            } catch (IndexOutOfBoundsException e) { //empty for MARK, UNMARK, TO DO description, DEADLINE description, EVENT description
                System.out.println("\tOOPS!!! You have missing arguments for " + words[0] + ".");
            }

            //return c;
        } catch (IllegalArgumentException e) {
            System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return c;

    }
}
