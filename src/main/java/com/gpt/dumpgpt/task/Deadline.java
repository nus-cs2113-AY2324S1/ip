package com.gpt.dumpgpt.task;

import com.gpt.dumpgpt.shared.DukeException;
import com.gpt.dumpgpt.shared.Serializer;

import java.io.IOException;

public class Deadline extends Task {
    private static final String TYPE = "Deadline";
    protected String by;

    public Deadline(String name, String by) {
        super(name, TYPE);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }

    @Override
    public void validate() throws DukeException {
        super.validate();
        if (by == null || by.isBlank()) {
            throw new DukeException("By cannot be empty...");
        }
    }

    @Override
    protected Serializer serialize() throws DukeException {
        Serializer serializer = super.serialize();
        serializer.putString(by);
        return serializer;
    }

    @Override
    protected void deserialize(Serializer serializer) throws DukeException, IOException {
        super.deserialize(serializer);
        by = serializer.readString();
    }
}
