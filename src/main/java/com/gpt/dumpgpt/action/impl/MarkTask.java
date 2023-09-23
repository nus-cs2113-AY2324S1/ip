package com.gpt.dumpgpt.action.impl;

import com.gpt.dumpgpt.action.api.Action;
import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.DukeException;
import com.gpt.dumpgpt.shared.Ui;
import com.gpt.dumpgpt.task.Task;
import com.gpt.dumpgpt.task.TaskManager;

import java.util.ArrayList;

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
     *
     * @param action string denoting the action type to be printed in error message
     * @param task   the task object to be checked
     * @throws DukeException exception thrown if task is null
     */
    protected void throwIfInvalidTask(String action, Task task) throws DukeException {
        if (task == null) {
            throw new DukeException(String.format("Failed to mark task as %s...", action));
        }
    }

    /**
     * Prints standard success message for task marking
     *
     * @param action string denoting the action type to be printed in success message
     * @param task   the task object that was operated upon
     */
    protected void printSuccess(Ui ui, String action, Task task) {
        ui.printWrapped(new String[]{
                String.format("Nice I've marked this task as %s:", action),
                task.toString()
        });
    }

    protected void execute(Ui ui) throws DukeException {
        TaskManager taskManager = new TaskManager();
        Task task = taskManager.getTask(getCommand());
        throwIfInvalidTask(PRINT_ACTION, task);
        task.markDone();
        printSuccess(ui, PRINT_ACTION, task);
        taskManager.setLastOperation(task);
    }
}
