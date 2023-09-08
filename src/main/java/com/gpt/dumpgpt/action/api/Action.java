package com.gpt.dumpgpt.action.api;

import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.ProgramConstants;

public class Action {
    private final String ACTION_VERB;
    private final Command COMMAND;

    public Action(Command command, String actionVerb) {
        ACTION_VERB = actionVerb;
        COMMAND = command;
    }

    protected Command getCommand() {
        return COMMAND;
    }

    protected String getActionVerb() {
        return ACTION_VERB;
    }

    protected String[] getAliases() {
        return null;
    }

    protected void execute() {
        ProgramConstants.printWrapped("Unimplemented command...");
    }
}
