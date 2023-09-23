package com.gpt.dumpgpt.action.impl;

import com.gpt.dumpgpt.action.api.Action;
import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.DukeException;
import com.gpt.dumpgpt.shared.ProgramConstants;
import com.gpt.dumpgpt.task.Deadline;
import com.gpt.dumpgpt.task.Event;
import com.gpt.dumpgpt.task.Task;
import com.gpt.dumpgpt.task.TaskManager;
import com.gpt.dumpgpt.task.Todo;

public class AddTask extends Action {
    private static final String SUCCESS_PROMPT = "Great! I've added the following task:";
    private static final String ACTION_VERB = "";
    private static final String[] ALIASES = new String[]{
            "deadline", "event", "todo"
    };

    public AddTask(Command command) {
        super(command, ACTION_VERB);
    }

    protected AddTask(Command command, String verb) {
        super(command, verb);
    }

    @Override
    protected String[] getAliases() {
        return ALIASES;
    }

    protected void execute() throws DukeException {
        TaskManager taskManager = new TaskManager();
        Task task = createNewTask();
        throwIfInvalidTask(task);
        taskManager.addTask(task);
        printSuccess(SUCCESS_PROMPT, taskManager, task);
    }

    private static void throwIfInvalidTask(Task task) throws DukeException {
        if (task == null) {
            throw new DukeException("Failed to add new task...");
        }
        task.validate();
    }

    private Task createNewTask() {
        Command command = getCommand();
        switch (command.getCommandVerb()) {
        case "deadline":
            return new Deadline(command.getArguments(), command.getOptions("by"));
        case "event":
            return new Event(command.getArguments(), command.getOptions("from"), command.getOptions("to"));
        case "todo":
            return new Todo(command.getArguments());
        }
        return null;
    }

    /**
     * Prints a standard success message
     * @param prompt string that will be printed as the before
     *               the standard task summary lines
     * @param taskManager instance of TaskManager
     * @param task the task that was operated upon
     */
    protected void printSuccess(String prompt, TaskManager taskManager, Task task) {
        int tasksCount = taskManager.getTasks().size();
        String taskSummary = String.format(
                "You now have %d %s in the list!",
                tasksCount,
                (tasksCount > 1) ? "tasks" : "task"
        );
        ProgramConstants.printWrapped(new String[]{
                prompt,
                "\t" + task.toString(),
                taskSummary
        });
    }
}
