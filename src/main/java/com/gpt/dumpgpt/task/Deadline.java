package com.gpt.dumpgpt.task;

import com.gpt.dumpgpt.shared.DukeException;

public class Deadline extends Task {
    private static final String TYPE = "Deadline";
    protected String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }

    @Override
    public void validate() throws DukeException {
        super.validate(TYPE);
        if (by == null || by.isBlank()) {
            throw new DukeException("By cannot be empty...");
        }
    }
}
