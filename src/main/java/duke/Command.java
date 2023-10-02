package duke;

import java.util.ArrayList;

public interface Command {
    void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException;
    boolean isExit();
}
