package duke.commands;

import duke.exception.DukeException;
import duke.tasks.Tasklist;
import duke.tasks.Event;

import duke.ui.Ui;
import static duke.ui.MessageConstants.MESSAGE_ADD;

public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public AddEventCommand(String description , String from , String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    public void execute(Tasklist tasks) throws DukeException {
        tasks.add(new Event(description, from, to));
        Ui.showMessage(MESSAGE_ADD + tasks.get(tasks.size() - 1).toString());
    }
    
}
