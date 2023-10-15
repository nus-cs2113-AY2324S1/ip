package duke.commands;

import duke.exception.DukeException;
import duke.tasks.Tasklist;

import duke.ui.Ui;
import static duke.ui.MessageConstants.MESSAGE_FIND;

/**
 * Represents a command to find tasks that contain a certain keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand object with the given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand on the given Tasklist object.
     *
     * @param tasks The Tasklist object to execute the command on.
     * @throws DukeException If there is an error executing the command.
     */
    public void execute(Tasklist tasks) throws DukeException {
        Ui.showMessage(MESSAGE_FIND + "\n" + tasks.findTasksToString(keyword));
    }
    
}