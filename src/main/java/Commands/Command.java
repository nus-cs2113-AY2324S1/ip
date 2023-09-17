package Commands;

import CSGPT.TaskList;
import CSGPT.Task;
import CSGPT.Todo;
import CSGPT.Event;
import CSGPT.Deadline;
import Exceptions.CSGPTException;
import Exceptions.CSGPTParsingException;

public abstract class Command {
    private static final String ADD_TODO_COMMAND = "todo";
    private static final String ADD_DEADLINE_COMMAND = "deadline";
    private static final String ADD_EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String FAREWELL_COMMAND = "bye";

    public static Command getCommand(String input) throws CSGPTParsingException {
        String initialInput;
        if (input.contains(" ")) {
            initialInput = input.split(" ")[0];
        } else {
            initialInput = input;
        }
        switch (initialInput) {
        case ADD_TODO_COMMAND:
            Task todo = getTodo(input);
            return new Add(todo);
        case ADD_DEADLINE_COMMAND:
            Task deadline = getDeadline(input);
            return new Add(deadline);
        case ADD_EVENT_COMMAND:
            Task event = getTask(input);
            return new Add(event);
        case DELETE_COMMAND:
            return getDelete(input);
        case LIST_COMMAND:
            return new List();
        case MARK_COMMAND:
            return getMark(input, true);
        case UNMARK_COMMAND:
            return getMark(input, false);
        case FAREWELL_COMMAND:
            return new Farewell();
        default:
            throw new CSGPTParsingException("I'm sorry mortal, but I don't know what that means.");
        }
    }

    private static Todo getTodo(String input) throws CSGPTParsingException {
        if (input.equals(ADD_TODO_COMMAND)) {
            throw new CSGPTParsingException("The description of a todo cannot be empty.");
        }

        String todoDescription = input.substring(ADD_TODO_COMMAND.length() + 1);

        return new Todo(todoDescription);
    }

    private static Task getDeadline(String input) throws CSGPTParsingException {
        if (input.equals(ADD_DEADLINE_COMMAND)) {
            throw new CSGPTParsingException("The description of a deadline cannot be empty.");
        }

        String deadlineDetails = input.substring(ADD_DEADLINE_COMMAND.length() + 1);
        if (!deadlineDetails.contains(" /by ")) {
            throw new CSGPTParsingException("Please enter a valid /by.");
        }
        String[] deadlineDetailsArray = deadlineDetails.split(" /by ", 2);
        String deadlineDescription = deadlineDetailsArray[0];
        if (deadlineDescription.isEmpty()) {
            throw new CSGPTParsingException("The description of a deadline cannot be empty.");
        }
        String by = deadlineDetailsArray[1];

        return new Deadline(deadlineDescription, by);
    }

    private static Task getTask(String input) throws CSGPTParsingException {
        if (input.equals(ADD_EVENT_COMMAND)) {
            throw new CSGPTParsingException("The description of a event cannot be empty.");
        }

        String eventDetails = input.substring(ADD_EVENT_COMMAND.length() + 1);
        if (!eventDetails.contains(" /from ")) {
            throw new CSGPTParsingException("Please enter a /from.");
        }
        String[] eventDetailsArray = input.substring(ADD_EVENT_COMMAND.length()).split(" /from ", 2);
        String eventDescription = eventDetailsArray[0];
        if (eventDescription.isEmpty()) {
            throw new CSGPTParsingException("The description of an event cannot be empty.");
        }
        if (!eventDetails.contains(" /to ")) {
            throw new CSGPTParsingException("Please enter a /to.");
        }
        String[] eventDetailsArray2 = eventDetailsArray[1].split(" /to ", 2);

        String from = eventDetailsArray2[0];
        String to = eventDetailsArray2[1];

        return new Event(eventDescription, from, to);
    }

    private static Delete getDelete(String input) throws CSGPTParsingException {
        String remainder = input.split(" ")[1];
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(remainder);
        } catch (NumberFormatException e) {
            throw new CSGPTParsingException("Please enter a valid number mortal.");
        }
        return new Delete(taskNumber);
    }

    private static Mark getMark(String input, boolean isDone) throws CSGPTParsingException {
        String remainder = input.split(" ")[1];
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(remainder);
        } catch (NumberFormatException e) {
            throw new CSGPTParsingException("Please enter a valid number mortal.");
        }
        return new Mark(taskNumber, isDone);
    }

    public abstract void execute(TaskList tasklist) throws CSGPTException;
}
