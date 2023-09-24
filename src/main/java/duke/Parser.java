package duke;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The Parser class is responsible for parsing user input and executing the corresponding actions.
 * It interprets user commands and delegates the execution to other classes within the Duke application.
 */
public class Parser {

    String[] indicators = {"list", "mark", "unmark", "delete", "todo", "deadline", "event", "find"};
    Set<String> indicatorSet = new HashSet<>(Arrays.asList(indicators));

    /**
     * Parses the user's input and performs the corresponding action.
     *
     * @param input The user's input command.
     * @throws DukeException If there's an issue parsing or executing the command.
     */
    public void parse(String input) throws DukeException {
        Itay.validateNonEmptyCommand(input);
        String splitInput[] = input.split(" ");
        String indicator = splitInput[0];
        String lowerCaseIndicator = indicator.toLowerCase();

        if(!indicatorSet.contains(lowerCaseIndicator)) {
            Itay.handleBadIndicator();
        }

        if(lowerCaseIndicator.equals("list")) {
            Itay.printList();
            return;
        }

        Itay.validateNonEmptyDescription(splitInput);

        switch(lowerCaseIndicator) {
        case("mark"):
            Itay.handleMark(splitInput);
            break;
        case("unmark"):
            Itay.handleUnmark(splitInput);
            break;
        case("delete"):
            Itay.handleDelete(splitInput);
            break;
        case("todo"):
            Itay.handleTodo(input, splitInput);
            break;
        case("deadline"):
            Itay.handleDeadline(input);
            break;
        case("event"):
            Itay.handleEvent(input);
            break;
        case("find"):
            Itay.handleFind(input, splitInput);
            break;
        }
    }
    
    public boolean isExit(String input) {
        return input.equals("bye");
    }
}