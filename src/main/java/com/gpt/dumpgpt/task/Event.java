package com.gpt.dumpgpt.task;

import com.gpt.dumpgpt.shared.DukeException;
import com.gpt.dumpgpt.shared.Serializer;

import java.io.IOException;

public class Event extends Task {
    private static final String TYPE = "Event";
    protected String from;
    protected String to;

    public Event(String name, String from, String to) {
        super(name, TYPE);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s | to: %s)", super.toString(), this.from, this.to);
    }

    @Override
    public void validate() throws DukeException {
        super.validate();
        if (from == null || from.isBlank()) {
            throw new DukeException("From cannot be empty...");
        } else if (to == null || to.isBlank()) {
            throw new DukeException("To cannot be empty");
        }
    }

    @Override
    public Serializer serialize() throws DukeException {
        Serializer serializer = super.serialize();
        serializer.putString(from);
        serializer.putString(to);
        return serializer;
    }

    @Override
    public void deserialize(Serializer serializer) throws DukeException, IOException {
        super.deserialize(serializer);
        from = serializer.readString();
        to = serializer.readString();
    }
}
