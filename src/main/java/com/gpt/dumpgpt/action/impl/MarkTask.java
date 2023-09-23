package com.gpt.dumpgpt.action.impl;

import com.gpt.dumpgpt.action.api.Action;
import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.DukeException;
import com.gpt.dumpgpt.shared.ProgramConstants;
import com.gpt.dumpgpt.task.Task;
import com.gpt.dumpgpt.task.TaskManager;

public class MarkTask extends Action {
    private static final String ACTION_VERB = "mark";
    private static final String PRINT_ACTION = "done";

    public MarkTask(Command command) {
        super(command, ACTION_VERB);
    }

    protected MarkTask(Command command, String verb) {
        super(command, verb);
    }

    /**
     * Ensures task is non-null
     * @param action string denoting the action type to be printed in error message
     * @param task the task object to be checked
     * @throws DukeException exception thrown if task is null
     */
    protected void throwIfInvalidTask(String action, Task task) throws DukeException {
        if (task == null) {
            throw new DukeException(String.format("Failed to mark task as %s...", action));
        }
    }

    /**
     * Prints standard success message for task marking
     * @param action string denoting the action type to be printed in success message
     * @param task the task object that was operated upon
     */
    protected void printSuccess(String action, Task task) {
        ProgramConstants.printWrapped(new String[]{
                String.format("Nice I've marked this task as %s:", action),
                task.toString()
        });
    }

    protected void execute() throws DukeException {
        TaskManager taskManager = new TaskManager();
        Task task = taskManager.getTask(getCommand());
        throwIfInvalidTask(PRINT_ACTION, task);
        task.markDone();
        printSuccess(PRINT_ACTION, task);
    }
}
