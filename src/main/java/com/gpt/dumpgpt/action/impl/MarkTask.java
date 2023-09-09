package com.gpt.dumpgpt.action.impl;

import com.gpt.dumpgpt.action.api.Action;
import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.ProgramConstants;
import com.gpt.dumpgpt.task.Task;

public class MarkTask extends Action {
    private static final String ACTION_VERB = "mark";
    private static final String PRINT_ACTION = "done";

    public MarkTask(Command command) {
        super(command, ACTION_VERB);
    }

    public MarkTask(Command command, String verb) {
        super(command, verb);
    }

    protected Task getTask() {
        Command command = getCommand();
        int taskNumber = ProgramConstants.parsePositiveNumber(command.getArguments());
        return Task.getTask(--taskNumber);
    }

    protected void printFailure(String action) {
        ProgramConstants.printWrapped(
                String.format("Failed to mark task as %s...", action)
        );
    }

    protected void printSuccess(String action, Task task) {
        ProgramConstants.printWrapped(new String[]{
                String.format("Nice I've marked this task as %s:", action),
                task.toString()
        });
    }

    protected void execute() {
        Task task = getTask();
        if (task == null) {
            printFailure(PRINT_ACTION);
            return;
        }

        task.markDone();
        printSuccess(PRINT_ACTION, task);
    }
}
