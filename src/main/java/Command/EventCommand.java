package Command;

import Soccat.Event;
import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

import java.io.IOException;

public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    public static final String INVALID_PROMPT = "Oops, please try event <task> /from <start> /to <end>!";

    private final Event eventTask;

    public EventCommand(String taskName, String from, String to) {
        this.eventTask = new Event(taskName, from, to);
    }

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