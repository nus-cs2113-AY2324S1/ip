package Ken;

import Commands.*;
import Exceptions.KenException;
import Exceptions.KenParsingException;

public class ParseCommands {
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String BYE = "bye";
    public Command parse(String input) throws KenException {
        String command = input.contains(" ") ? input.split(" ")[0] : input;
        switch (command) {
        case TODO:
            Task todo = getTodo(input);
            return new Add(todo);
        case DEADLINE:
            Task deadline = getDeadline(input);
            return new Add(deadline);
        case EVENT:
            Task event = getEvent(input);
            return new Add(event);
        case LIST:
            return new List();
        case MARK:
            return getStatus(input, true);
        case UNMARK:
            return getStatus(input, false);
        case BYE:
            return new Goodbye();
        default:
            throw new KenParsingException("Uh-oh, darling! Your input needs a makeover for me to understand!");
        }

    }

    private static Task getTodo(String input) throws KenParsingException {
        if (input.trim().equals(TODO)) {
            throw new KenParsingException("Oopsie! A todo without a description is like a party without glitter, so not fabulous!");
        }
        String todoName = input.substring(TODO.length() + 1);
        return new Todo(todoName);
    }

    private static Task getDeadline(String input) throws KenParsingException {
        if (!input.contains("/by")) {
            throw new KenParsingException("Please enter a proper deadline using '/by'.");
        }
        String[] deadlineInfo = input.substring(DEADLINE.length() + 1).split("/by", 2);
        String deadlineName = deadlineInfo[0];
        String by = deadlineInfo[1];
        if (deadlineName.isEmpty()) {
            throw new KenParsingException("Oopsie! A deadline without a description is like a party without glitter, so not fabulous!");
        }
        if (by.isEmpty()) {
            throw new KenParsingException("Oopsie! Did you forget to include the deadline?");
        }
        return new Deadline(deadlineName, by);
    }

    private static Task getEvent(String input) throws KenParsingException {
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new KenParsingException("Please enter a proper event using '/from' and ' /to'.");
        }
        String[] eventInfo = input.substring(EVENT.length() + 1).split("/from |/to", 3);
        String eventName = eventInfo[0];
        String from = eventInfo[1];
        String to = eventInfo[2];
        if (eventName.isEmpty()) {
            throw new KenParsingException("Oopsie! An event without a description is like a party without glitter, so not fabulous!");
        }
        if (from.isEmpty()) {
            throw new KenParsingException("Oopsie! Did you forget to include the start date of the event?");
        }
        if (to.isEmpty()) {
            throw new KenParsingException("Oopsie! Did you forget to include the end date of the event?");
        }
        return new Event(eventName, from, to);
    }

    public static Update getStatus(String input, boolean isDone) throws KenParsingException{
        String trimmedInput = input.trim();
        if (trimmedInput.equals(MARK) || trimmedInput.equals(UNMARK)) {
            throw new KenParsingException("Pick a task to mark/unmark and let the Barbie magic flow!");
        }
        String taskString = input.split(" ", 2)[1];
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(taskString);
        } catch (NumberFormatException e) {
            throw new KenParsingException("Oh darling, that's not a number fit for a Barbie world.");
        }
        return new Update(taskNumber, isDone);
    }
}
