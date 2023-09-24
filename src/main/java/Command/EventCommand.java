package Command;

import Soccat.Event;
import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

import java.io.IOException;

/**
 * Represents a command to add an event.
 * A <code>EventCommand</code> corresponds to a command
 * consisting of an event with a <code>task</code>, <code>start</code> and <code>end</code>.
 * */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    public static final String INVALID_PROMPT = "Oops, please try event <task> /from <start> /to <end>!";

    private final Event eventTask;

    public EventCommand(String taskName, String from, String to) {
        this.eventTask = new Event(taskName, from, to);
    }

    /**
     * Executes the event command to add an event task
     * to the list of tasks, and update the storage file.
     *
     * @param tasks The taskList object containing tasks
     * @param ui The ui object to display messages to users
     * @param taskFile The storage file for tasks to be stored
     * @return Boolean of whether to exit the application.
     * */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage taskFile) {
        ui.displayLine();
        try {
            tasks.addTask(eventTask, taskFile);
            ui.displayAddedTask(eventTask, tasks, COMMAND_WORD);
        } catch (IOException e) {
            ui.displayError(ui.IO_EXCEPTION_MESSAGE);
        }
        return false;
    }
}