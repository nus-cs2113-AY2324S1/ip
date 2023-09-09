package com.gpt.dumpgpt.task;

import com.gpt.dumpgpt.shared.DukeException;

public class Todo extends Task {
    private static final String TYPE = "Todo";

    public Todo(String name) {
        super(name);
    }

    @Override
    public void validate() throws DukeException {
        super.validate(TYPE);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
