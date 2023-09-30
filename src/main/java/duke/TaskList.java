package duke;

import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Collection;

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
