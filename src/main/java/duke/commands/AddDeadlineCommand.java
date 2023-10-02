package duke.commands;

import duke.exception.DukeException;
import duke.tasks.Tasklist;
import duke.tasks.Deadline;

import duke.ui.Ui;
import static duke.ui.MessageConstants.MESSAGE_ADD;

/**
 * Represents a command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructs a new AddDeadlineCommand object with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline of the deadline task.
     */
    public AddDeadlineCommand(String description , String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the AddDeadlineCommand by adding a new deadline task to the Tasklist.
     *
     * @param tasks The Tasklist object to modify.
     * @throws DukeException If there is an error adding the deadline task.
     */
    @Override
    public void execute(Tasklist tasks) throws DukeException {
        tasks.add(new Deadline(description , by));
        Ui.showMessage(MESSAGE_ADD + tasks.get(tasks.size() - 1).toString());
    }
}