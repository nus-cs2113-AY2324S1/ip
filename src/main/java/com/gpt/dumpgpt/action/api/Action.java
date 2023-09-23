package com.gpt.dumpgpt.action.api;

import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.DukeException;

public abstract class Action {
    private final String ACTION_VERB;
    private final Command COMMAND;

    public Action(Command command, String actionVerb) {
        ACTION_VERB = actionVerb;
        COMMAND = command;
    }

    /**
     * Returns the command object passed when
     * constructing this action instance
     * @return Command object passed to constructor
     */
    protected Command getCommand() {
        return COMMAND;
    }

    /**
     * Returns the action verb string passed when
     * constructing this action instance
     * @return action verb string
     */
    protected String getActionVerb() {
        return ACTION_VERB;
    }

    /**
     * Returns aliases associated with this action
     * @return string array of aliases
     */
    protected String[] getAliases() {
        return null;
    }

    /**
     * Executes the action
     * @throws DukeException any exception from execution
     */
    protected abstract void execute() throws DukeException;
}
