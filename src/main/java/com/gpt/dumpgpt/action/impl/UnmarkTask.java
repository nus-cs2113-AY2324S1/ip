package com.gpt.dumpgpt.action.impl;

import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.DukeException;
import com.gpt.dumpgpt.task.Task;

public class UnmarkTask extends MarkTask {
    private static final String ACTION_VERB = "unmark";
    private static final String PRINT_ACTION = "undone";

    public UnmarkTask(Command command) {
        super(command, ACTION_VERB);
    }

    @Override
    protected void execute() throws DukeException {
        Task task = getTask();
        throwIfInvalidTask(PRINT_ACTION, task);
        task.unmarkDone();
        printSuccess(PRINT_ACTION, task);
    }
}
