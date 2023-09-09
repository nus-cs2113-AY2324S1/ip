package com.gpt.dumpgpt.task;

import com.gpt.dumpgpt.shared.DukeException;

public class Event extends Task {
    private static final String TYPE = "Event";
    protected String from;
    protected String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s | to: %s)", super.toString(), this.from, this.to);
    }

    @Override
    public void validate() throws DukeException {
        super.validate(TYPE);
        if (from == null || from.isBlank()) {
            throw new DukeException("From cannot be empty...");
        } else if (to == null || to.isBlank()) {
            throw new DukeException("To cannot be empty");
        }
    }
}
