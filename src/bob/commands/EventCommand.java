package bob.commands;

import bob.BobException;
import bob.event.Event;
import bob.tasklist.TaskList;
import bob.todo.Todo;

public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    private final Event newEvent;

    public EventCommand(Event event) {
        newEvent = event;
    }

    public EventCommand(String line) {
        int fromIdx = line.indexOf("/from");
        int toIdx = line.indexOf("/to");

        // Extract task description, start time and end time from user input
        String description = line.substring(0, fromIdx-1);
        String start = line.substring(fromIdx+ "/from ".length(), toIdx-1);
        String end = line.substring(toIdx+ "/to ".length());

        newEvent = new Event(description, start, end);
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.handleCreateEvent(newEvent);
    }

}