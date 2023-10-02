package duke.commands;

import duke.exception.DukeException;
import duke.tasks.Tasklist;
import duke.tasks.Event;

import duke.ui.Ui;
import static duke.ui.MessageConstants.MESSAGE_ADD;

/**
 * Represents a command to add an event task.
 */
public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs a new AddEventCommand object with the given description, start time and end time.
     *
     * @param description The description of the event task.
     * @param from The start time of the event task.
     * @param to The end time of the event task.
     */
    public AddEventCommand(String description , String from , String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the AddEventCommand by adding a new event task to the Tasklist.
     *
     * @param tasks The Tasklist object to modify.
     * @throws DukeException If there is an error adding the event task.
     */
    @Override
    public void execute(Tasklist tasks) throws DukeException {
        tasks.add(new Event(description, from, to));
        Ui.showMessage(MESSAGE_ADD + tasks.get(tasks.size() - 1).toString());
    }
    
}