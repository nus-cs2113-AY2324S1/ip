package com.gpt.dumpgpt.action.impl;

import com.gpt.dumpgpt.action.api.Action;
import com.gpt.dumpgpt.command.Command;
import com.gpt.dumpgpt.shared.ProgramConstants;
import com.gpt.dumpgpt.shared.Ui;

public class EndProgram extends Action {
    private static final String ACTION_VERB = "bye";

    public EndProgram(Command command) {
        super(command, ACTION_VERB);
    }

    protected void execute(Ui ui) {
        ui.printWrapped("Bye. Hope to see you again soon!");
        ProgramConstants.setIsEnded(true);
    }
}
