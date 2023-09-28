package com.gpt.dumpgpt.action.impl;

import com.gpt.dumpgpt.command.Command;

import com.gpt.dumpgpt.shared.DukeException;
import com.gpt.dumpgpt.shared.Ui;
import com.gpt.dumpgpt.task.Task;
import com.gpt.dumpgpt.task.TaskManager;

public class UnmarkTask extends MarkTask {
    private static final String ACTION_VERB = "unmark";
    private static final String PRINT_ACTION = "undone";

    public UnmarkTask(Command command) {
        super(command, ACTION_VERB);
    }

    @Override
    protected void execute(Ui ui) throws DukeException {
        TaskManager taskManager = new TaskManager();
        Task task = taskManager.getTask(getCommand());
        throwIfInvalidTask(PRINT_ACTION, task);
        task.unmarkDone();
        printSuccess(ui, PRINT_ACTION, task);
        taskManager.setLastOperation(task);
    }
}
