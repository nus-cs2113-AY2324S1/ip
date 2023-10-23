package duke;

import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Collection;

/**
 * List to store all tasks added by the user
 */
public class TaskList extends ArrayList<Task> {
    public TaskList() {
    }

    public TaskList(Collection<? extends Task> c) throws DukeException {
        super(c);
        if (c == null) {
            throw new DukeException("Nothing in there!");
        }
    }
}
