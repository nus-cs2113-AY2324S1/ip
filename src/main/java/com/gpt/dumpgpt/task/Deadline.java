package com.gpt.dumpgpt.task;

import com.gpt.dumpgpt.command.Parser;
import com.gpt.dumpgpt.shared.DukeDateTime;
import com.gpt.dumpgpt.shared.DukeException;
import com.gpt.dumpgpt.shared.ProgramConstants;
import com.gpt.dumpgpt.shared.Serializer;

import java.io.IOException;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private static final String TYPE = "Deadline";
    protected DukeDateTime by;

    public Deadline(String name, String by) throws DukeException {
        super(name, TYPE);
        this.by = new DukeDateTime(by);
    }

    @Override
    public void validate() throws DukeException {
        super.validate();

        if (by.getOriginalDatetime() == null) {
            throw new DukeException("By cannot be empty...");
        }
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                by
        );
    }

    @Override
    public Serializer serialize() throws DukeException, IOException {
        Serializer serializer = super.serialize();
        serializer.putSerializable(by);
        return serializer;
    }

    @Override
    public void deserialize(Serializer serializer) throws DukeException, IOException {
        super.deserialize(serializer);
        by = new DukeDateTime();
        by.deserialize(serializer.readSerializable());
    }
}
