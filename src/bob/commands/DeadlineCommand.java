package bob.commands;

import bob.BobException;
import bob.deadline.Deadline;
import bob.tasklist.TaskList;

import java.time.LocalDate;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    private final Deadline newDeadline;

    public DeadlineCommand(Deadline deadline) {
        newDeadline = deadline;
    }

    public DeadlineCommand(String line) throws BobException {
        int byIdx = line.indexOf("/by");
        if (byIdx == -1) {
            throw new BobException("The /by of a deadline must be specified");
        }

        // Extract task description and deadline from user input
        if (byIdx == 0) {
            throw new BobException("The description of a deadline cannot be empty");
        }

        String description = line.substring(0,byIdx-1);
        if (description.trim().isEmpty()) {
            throw new BobException("The description of a deadline cannot be empty");
        }

        int deadlineIdx = byIdx+ "/by ".length();
        if (deadlineIdx >= line.length()) {
            throw new BobException("The /by of a deadline cannot be empty");
        }

        LocalDate deadline = LocalDate.parse(line.substring(deadlineIdx));

        newDeadline = new Deadline(description, deadline);
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.handleCreateDeadline(newDeadline);
    }
}
