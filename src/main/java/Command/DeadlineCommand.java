package Command;

import Soccat.Deadline;
import Storage.TaskList;
import Storage.Storage;
import Ui.Ui;

import java.io.IOException;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String INVALID_PROMPT = "Oops, please try deadline <task> /by <deadline>!";

    private final Deadline deadlineTask;

    public DeadlineCommand(String taskName, String deadline) {
        this.deadlineTask = new Deadline(taskName, deadline);
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage taskFile) {
        ui.displayLine();
        try {
            tasks.addTask(deadlineTask, taskFile);
            ui.displayAddedTask(deadlineTask, tasks, COMMAND_WORD);
        } catch (IOException e) {
            ui.displayError(ui.IO_EXCEPTION_MESSAGE);
        }
        return false;
    }
}