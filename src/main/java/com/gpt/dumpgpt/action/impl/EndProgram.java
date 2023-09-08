package com.gpt.dumpgpt.action.impl;

import com.gpt.dumpgpt.action.api.Action;
import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.ApplicationState;
import com.gpt.dumpgpt.shared.ProgramConstants;

public class EndProgram extends Action {
    private static final String ACTION_VERB = "bye";

    public EndProgram(Command command) {
        super(command, ACTION_VERB);
    }

    @Override
    protected void execute() {
        ProgramConstants.printWrapped("Bye. Hope to see you again soon!");
        ApplicationState.getAppState().setApplicationEnded(true);
    }
}
