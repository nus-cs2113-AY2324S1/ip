package command;

import task.Event;
import task.TaskList;
import exception.FrankException;
import utility.Ui;

public class EventCommand extends Command {
    /**
     * Creates and adds an Event to the TaskList
     * @param tasks TaskList of current Tasks
     * @param ui Current User Interface
     * @throws FrankException Unique Exceptions
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws FrankException {
        String[] descStartEnd = ui.getEvent();
        tasks.addTask(new Event(descStartEnd[0], descStartEnd[1], descStartEnd[2]));
    }
}