package com.gpt.dumpgpt.action.impl;

import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.task.Task;

public class UnmarkTask extends MarkTask {
    private static final String ACTION_VERB = "unmark";
    private static final String PRINT_ACTION = "undone";

    public UnmarkTask(Command command) {
        super(command, ACTION_VERB);
    }

    @Override
    protected void execute() {
        Task task = getTask();
        if (task == null) {
            printFailure(PRINT_ACTION);
            return;
        }

        task.unmarkDone();
        printSuccess(PRINT_ACTION, task);
    }
}
