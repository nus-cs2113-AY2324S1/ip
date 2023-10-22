package bob.commands;

import bob.event.Event;
import bob.tasklist.TaskList;

/**
 * Adds a new event to task list.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    private final Event newEvent;

    /**
     * Creates a new EventCommand to add a new {@code Event}.
     *
     * @param event Event to add into task list.
     */
    public EventCommand(Event event) {
        newEvent = event;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.handleCreateEvent(newEvent);
    }

}