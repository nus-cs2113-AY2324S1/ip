package com.gpt.dumpgpt.task;

import com.gpt.dumpgpt.shared.DukeException;

public class Todo extends Task {
    private static final String TYPE = "Todo";

    public Todo(String name) {
        super(name, TYPE);
    }

    @Override
    public void validate() throws DukeException {
        super.validate();
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
