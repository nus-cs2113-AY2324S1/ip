package parser;

import skippy.Skippy;


public class SkippyParser {

    public Skippy skippy;

    /**
     * Creates an instance of SkippyPaser
     *
     * @param skippy current instance of Skippy.
     */
    public SkippyParser(Skippy skippy) {
        this.skippy = skippy;
    }

    /**
     * Parsers the user input and calls the corresponding command methods.
     *
     * @param input unformatted user input from the terminal
     */
    public void parseUserInput(String input) {
        String[] inputWords = input.trim().split(" ");
        String command = inputWords[0].toLowerCase();

        switch (command) {
        case "bye":
            skippy.enteredBye = true;
            skippy.exit();
            break;
        case "list":
            skippy.ui.printTaskList(skippy.taskList);
            break;
        case "help":
            skippy.ui.printHelp();
            break;
        case "todo":
            skippy.taskList.addToDoTask(input);
            break;
        case "deadline":
            skippy.taskList.addDeadline(input);
            break;
        case "event":
            skippy.taskList.addEvent(input);
            break;
        case "mark":
            skippy.taskList.markAsDone(inputWords);
            break;
        case "unmark":
            skippy.taskList.unmark(inputWords);
            break;
        case "delete":
            skippy.taskList.deleteTask(inputWords);
            break;
        case "find":
            skippy.taskList.findTask(inputWords);
            break;
        default:
            skippy.ui.printInvalidInputMessage();
            break;
        }
    }
}
