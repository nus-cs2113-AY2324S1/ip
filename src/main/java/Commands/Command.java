package Commands;

import CSGPT.TaskList;
import CSGPT.Task;
import CSGPT.Todo;
import CSGPT.Event;
import CSGPT.Deadline;
import CSGPT.CSGPT;
import Exceptions.CSGPTException;

public abstract class Command {
    private static final String ADD_TODO_COMMAND = "todo";
    private static final String ADD_DEADLINE_COMMAND = "deadline";
    private static final String ADD_EVENT_COMMAND = "event";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String FAREWELL_COMMAND = "bye";

    public static Command getCommand(String input, TaskList taskList) {
        switch (input.contains(" ") ? input.split(" ")[0] : input) {
        case ADD_TODO_COMMAND:
            if (input.equals(ADD_TODO_COMMAND)) {
                return new Echo("The description of a todo cannot be empty.");
            }

            String todoDescription = input.substring(ADD_TODO_COMMAND.length() + 1);
            Task todo = new Todo(todoDescription);
            return new Add(todo, taskList);
        case ADD_DEADLINE_COMMAND:
            if (input.equals(ADD_DEADLINE_COMMAND)) {
                return new Echo("The description of a deadline cannot be empty.");
            }

            String deadlineDetails = input.substring(ADD_DEADLINE_COMMAND.length() + 1);
            if (!deadlineDetails.contains(" /by ")) {
                return new Echo("Please enter a valid /by.");
            }
            String[] deadlineDetailsArray = deadlineDetails.split(" /by ", 2);
            String deadlineDescription = deadlineDetailsArray[0];
            if (deadlineDescription.isEmpty()) {
                return new Echo("The description of a deadline cannot be empty.");
            }
            String by = deadlineDetailsArray[1];

            Task deadline = new Deadline(deadlineDescription, by);
            return new Add(deadline, taskList);
        case ADD_EVENT_COMMAND:
            if (input.equals(ADD_EVENT_COMMAND)) {
                return new Echo("The description of a event cannot be empty.");
            }

            String eventDetails = input.substring(ADD_EVENT_COMMAND.length() + 1);
            if (!eventDetails.contains(" /from ")) {
                return new Echo("Please enter a /from.");
            }
            String[] eventDetailsArray = input.substring(ADD_EVENT_COMMAND.length()).split(" /from ", 2);
            String eventDescription = eventDetailsArray[0];
            if (eventDescription.isEmpty()) {
                return new Echo("The description of an event cannot be empty.");
            }
            if (!eventDetails.contains(" /to ")) {
                return new Echo("Please enter a /to.");
            }
            String[] eventDetailsArray2 = eventDetailsArray[1].split(" /to ", 2);

            String from = eventDetailsArray2[0];
            String to = eventDetailsArray2[1];

            Task event = new Event(eventDescription, from, to);
            return new Add(event, taskList);
        case LIST_COMMAND:
            return new List(taskList);
        case MARK_COMMAND:
            String remainder = input.split(" ")[1];
            int taskNumber;
            try {
                taskNumber = Integer.parseInt(remainder);
            } catch (NumberFormatException e) {
                String output = "Please enter a valid task number mortal.";
                return new Echo(output);
            }
            return new Mark(taskNumber, taskList);
        case FAREWELL_COMMAND:
            return new Farewell();
        default:
            return new Echo(input);
        }
    }
    public abstract void execute() throws CSGPTException;
}
