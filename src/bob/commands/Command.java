package bob.commands;

import bob.tasklist.TaskList;

public abstract class Command {

    /**
     * Executes command.
     *
     * @param taskList List of tasks upon which command will execute on.
     * @return Feedback from executed command.
     */
    public abstract String execute(TaskList taskList);

}
