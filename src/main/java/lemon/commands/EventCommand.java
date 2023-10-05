package lemon.commands;

import lemon.file.Storage;
import lemon.task.Event;
import lemon.task.TaskList;
import lemon.ui.UI;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    private final String description;
    private final String start;
    private final String end;

    public EventCommand(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Event event = new Event(description, start, end, false);
        tasks.addTask(event);
        ui.displayAddedTask(event, tasks);
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
