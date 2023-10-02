package duke.commands;

import duke.exception.DukeException;
import duke.tasks.Tasklist;
import duke.tasks.Deadline;

import duke.ui.Ui;
import static duke.ui.MessageConstants.MESSAGE_ADD;

public class AddDeadlineCommand extends Command {
    private String description;
    private String by;

    public AddDeadlineCommand(String description , String by) {
        this.description = description;
        this.by = by;
    }

    public void execute(Tasklist tasks) throws DukeException {
        tasks.add(new Deadline(description , by));
        Ui.showMessage(MESSAGE_ADD + tasks.get(tasks.size() - 1).toString());
    }
    

}
