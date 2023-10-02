package duke.commands;

import duke.exception.DukeException;
import duke.tasks.Tasklist;
import duke.tasks.Todo;

import duke.ui.Ui;
import static duke.ui.MessageConstants.MESSAGE_ADD;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    public void execute(Tasklist tasks) throws DukeException {
        tasks.add(new Todo(description));
        Ui.showMessage(MESSAGE_ADD + tasks.get(tasks.size() - 1).toString());
    }
    
}
