package com.gpt.dumpgpt.action.impl;

import com.gpt.dumpgpt.action.api.Action;
import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.DukeException;
import com.gpt.dumpgpt.shared.ProgramConstants;
import com.gpt.dumpgpt.task.Deadline;
import com.gpt.dumpgpt.task.Event;
import com.gpt.dumpgpt.task.Task;
import com.gpt.dumpgpt.task.Todo;

public class DeleteTask extends Action {
    private static final String ACTION_VERB = "delete";

    public DeleteTask(Command command) {
        super(command, ACTION_VERB);
    }

    protected void execute() throws DukeException {
        Task task = Task.getTask(getCommand());
        throwIfInvalidTask(task);
        if (Task.deleteTask(task)) {
            printSuccess(task);
            return;
        }

        // Something unexpected happened
        throw new DukeException("Failed to delete task...");
    }

    private static void throwIfInvalidTask(Task task) throws DukeException {
        if (task == null) {
            throw new DukeException("Invalid task number provided for deletion...");
        }
    }

    private void printSuccess(Task task) {
        int tasksCount = Task.getTasks().size();
        String taskSummary = String.format(
                "You now have %d %s in the list!",
                tasksCount,
                (tasksCount > 1) ? "tasks" : "task"
        );
        ProgramConstants.printWrapped(new String[]{
                "I've deleted the following task:",
                "\t" + task.toString(),
                taskSummary
        });
    }
}
