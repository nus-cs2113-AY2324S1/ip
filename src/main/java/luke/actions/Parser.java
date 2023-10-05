package luke.actions;

import luke.user.LukeTimeError;

/**
 * The Parser Class handles the parsing of user input into valid commands.
 */
public class Parser{
    //user input from Ui to luke command to Command

    /**
     * Parses the user's full command and returns an appropriate Command object.
     *
     * @param fullCommand The full user input command.
     * @return A Command object corresponding to the parsed command.
     * @throws LukeTimeError If an error specific to the LukeTime application occurs during parsing.
     */
    public static Command parse(String fullCommand) throws LukeTimeError {
        ActionType theAction;
        String parameters;

        String[] words = fullCommand.split(" ");
        //to get parameters for actions "mark", "unmark" & "delete"
        Command c;
        try {
            ActionType action = ActionType.valueOf(words[0].toUpperCase());
            theAction = action;

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
                        try {
                            c = new AddCommand(theAction, parameters);
                        } catch (LukeTimeError e) {
                            c = new DoNothingCommand(ActionType.LIST, parameters);
                        }
                        break;

                    case EVENT:
                        parameters = fullCommand.substring(6);
                        try {
                            c = new AddCommand(theAction, parameters);
                        } catch (LukeTimeError e) {
                            c = new DoNothingCommand(ActionType.LIST, parameters);
                        }
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
                        c = new DoNothingCommand(theAction, "");
                        assert false: "This line should never be reached";
                        break;
                }
            } catch (IndexOutOfBoundsException e) { //empty for MARK, UNMARK, TO DO description, DEADLINE description, EVENT description
                System.out.println("\tOOPS!!! You have missing arguments for " + words[0] + ". No changes have been made.");
                c = new DoNothingCommand(ActionType.LIST, "");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
            c = new DoNothingCommand(ActionType.LIST, "");
        }
        return c;
    }
}
