package doli.commands;

import doli.exceptions.DoliExceptions;

public class Parser {
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final int NR_EVENT_ARGS = 3;
    private static final int NR_DEADLINE_ARGS = 2;
    private static final int TASK_DESCRIPTION_IS_EMPTY = 0;
    public Parser() {
    }
    private static String[] splitCommandAndDetails(String input) {
        String[] commandAndDetails = input.toLowerCase().split(" ", 2);
        if (commandAndDetails.length == 2) {
            return commandAndDetails;
        } else {
            return commandAndDetails = new String[]{commandAndDetails[0], ""};
        }
    }
    public static Command parseInputIntoCommand(String input) throws DoliExceptions {
        final String command = splitCommandAndDetails(input)[0];
        final String details = splitCommandAndDetails(input)[1];
        final String[] args = details.split("/");
        checkForValidInput(command, args);
        Command newCommand = new Command(command, args);
        return newCommand;
    }
    public static void checkForValidInput(String command, String[] args) throws DoliExceptions {
        switch (command) {
        case TODO_COMMAND:
            if (args.length == TASK_DESCRIPTION_IS_EMPTY) {
                throw new DoliExceptions("Input of a todo cannot be blank!");
            }
            break;
        case DEADLINE_COMMAND:
            if (args.length < NR_DEADLINE_ARGS) {
                throw new DoliExceptions("Time or description missing for deadline");
            }
            break;
        case EVENT_COMMAND:
            if (args.length < NR_EVENT_ARGS) {
                throw new DoliExceptions("Starttime, endtime or desciption missing for event");
            }
            break;
        case MARK_COMMAND:
            if (args.length == 0) {
                throw new DoliExceptions("Please specify the index of the task to mark");
            }
            break;
        case UNMARK_COMMAND:
            if (args.length == 0) {
                throw new DoliExceptions("Please specify the index of the task to unmark");
            }
            break;
        default:
            break;
        }
    }
}
